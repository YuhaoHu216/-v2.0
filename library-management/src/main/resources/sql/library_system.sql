-- 图书馆借阅管理系统数据库设计
-- 1. 创建数据库
CREATE DATABASE IF NOT EXISTS library_management_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE library_management_system;

-- 2. 创建基本表
-- 读者表
CREATE TABLE readers (
    reader_id INT PRIMARY KEY AUTO_INCREMENT,
    reader_name VARCHAR(50) NOT NULL,
    reader_type ENUM('学生', '教师', '职工') NOT NULL,
    department VARCHAR(50),
    phone_number VARCHAR(20),
    status ENUM('正常', '挂失', '注销') DEFAULT '正常',
    max_borrow_count INT DEFAULT 5,
    current_borrow_count INT DEFAULT 0
);

-- 图书表
CREATE TABLE books (
    isbn VARCHAR(20) UNIQUE,
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100),
    publish_date DATE,
    category VARCHAR(50),
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1,
    location VARCHAR(100),
    status ENUM('在馆', '借出') DEFAULT '在馆'
);

-- 借阅记录表
CREATE TABLE borrow_records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    reader_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    renew_count INT DEFAULT 0,
    status ENUM('借阅中', '已归还', '逾期') DEFAULT '借阅中',
    fine_amount DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (reader_id) REFERENCES readers(reader_id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

-- 管理员表
CREATE TABLE administrators (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    real_name VARCHAR(50) NOT NULL,
    permission_level ENUM('普通管理员', '超级管理员') DEFAULT '普通管理员',
    last_login DATETIME
);

-- 3. 创建索引
CREATE INDEX idx_readers_name ON readers(reader_name);
CREATE INDEX idx_readers_type ON readers(reader_type);
CREATE INDEX idx_books_title ON books(title);
CREATE INDEX idx_books_author ON books(author);
CREATE INDEX idx_books_category ON books(category);
CREATE INDEX idx_borrow_records_dates ON borrow_records(borrow_date, due_date, return_date);
CREATE INDEX idx_borrow_records_status ON borrow_records(status);

-- 4. 创建视图
-- 图书借阅状态视图
CREATE VIEW book_borrow_status AS
SELECT 
    b.book_id,
    b.isbn,
    b.title,
    b.author,
    b.available_copies,
    b.total_copies,
    b.status as book_status,
    COUNT(br.record_id) as current_borrow_count
FROM books b
LEFT JOIN borrow_records br ON b.book_id = br.book_id AND br.status = '借阅中'
GROUP BY b.book_id;

-- 读者借阅详情视图
CREATE VIEW reader_borrow_details AS
SELECT 
    r.reader_id,
    r.reader_name,
    r.reader_type,
    r.current_borrow_count,
    r.max_borrow_count,
    br.record_id,
    b.title as book_title,
    br.borrow_date,
    br.due_date,
    br.status as borrow_status,
    br.fine_amount
FROM readers r
LEFT JOIN borrow_records br ON r.reader_id = br.reader_id
LEFT JOIN books b ON br.book_id = b.book_id;

-- 逾期记录视图
CREATE VIEW overdue_records AS
SELECT 
    br.record_id,
    r.reader_name,
    r.reader_type,
    b.title as book_title,
    br.borrow_date,
    br.due_date,
    DATEDIFF(CURDATE(), br.due_date) as overdue_days,
    br.fine_amount
FROM borrow_records br
JOIN readers r ON br.reader_id = r.reader_id
JOIN books b ON br.book_id = b.book_id
WHERE br.status = '借阅中' AND br.due_date < CURDATE();

-- 5. 插入测试数据
-- 插入读者数据
INSERT INTO readers (reader_name, reader_type, department, phone_number) VALUES
('张三', '学生', '计算机学院', '13800138001'),
('李四', '教师', '文学院', '13800138002'),
('王五', '职工', '图书馆', '13800138003');

-- 插入图书数据
INSERT INTO books (isbn, title, author, publisher,  category, total_copies, available_copies, location) VALUES
('9787115549441', '数据库系统概论', '王珊', '高等教育出版社', '计算机', 10, 8, 'A区-计算机-001'),
('9787302517735', 'Java编程思想', 'Bruce Eckel', '机械工业出版社',  '计算机', 5, 3, 'A区-计算机-002'),
('9787020167096', '红楼梦', '曹雪芹', '人民文学出版社', '文学', 8, 6, 'B区-文学-001');

-- 插入借阅记录
INSERT INTO borrow_records (reader_id, book_id, borrow_date, due_date, status) VALUES
(1, 1, '2024-09-01', '2024-09-15', '借阅中'),
(2, 2, '2024-09-05', '2024-09-19', '借阅中'),
(3, 3, '2024-08-20', '2024-09-03', '已归还');

-- 插入管理员数据
INSERT INTO administrators (username, password_hash, real_name, permission_level) VALUES
('admin', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '系统管理员', '超级管理员'),
('libadmin', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '图书管理员', '普通管理员');

-- 6. 常用查询示例
-- 查询所有在馆图书
SELECT * FROM books WHERE status = '在馆' AND available_copies > 0;

-- 查询读者借阅情况
SELECT reader_name, COUNT(*) as borrow_count 
FROM reader_borrow_details 
WHERE borrow_status = '借阅中' 
GROUP BY reader_id;

-- 查询逾期记录
SELECT * FROM overdue_records;

-- 更新图书可用数量（借书时调用）
UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?;
UPDATE readers SET current_borrow_count = current_borrow_count + 1 WHERE reader_id = ?;

-- 更新图书可用数量（还书时调用）
UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?;
UPDATE readers SET current_borrow_count = current_borrow_count - 1 WHERE reader_id = ?;