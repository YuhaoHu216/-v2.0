package hu.query;

import lombok.Data;

@Data
public class ReaderQuery {
    private Integer readerId;
    private String readerName;
    private String department;
    private String phoneNumber;
    private Integer readerType;
    private Integer status;
    private String account;
    private Integer page = 1;
    private Integer pageSize = 10;
}
