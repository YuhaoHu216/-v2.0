package hu.pojo;

import hu.enums.ReaderStatus;
import hu.enums.ReaderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//代替getter和setter方法
@NoArgsConstructor//代替空参构造
@AllArgsConstructor//代替全参构造
public class Reader {
    private Integer readerId;
    private String readName;
    private ReaderType readerType;
    private String department;
    private String phoneNumber;
    private ReaderStatus status;
    private Integer maxBorrowCount;
    private Integer currentBorrowCount;
}
