package hu.query;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminQuery {
    private Integer adminId;
    private String username;
    private String realName;
    private Integer adminType = 0;
    private LocalDate startTime;
    private LocalDate endTime;
}
