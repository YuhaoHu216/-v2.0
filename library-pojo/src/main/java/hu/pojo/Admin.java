package hu.pojo;

import hu.enums.PermissionLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Admin {
    private Integer adminId;
    private String username;
    private String password_hash;
    private String realName;
    private PermissionLevel permissionLevel;
    @DateTimeFormat(pattern = "YY-mm-DD HH:mm:ss")
    private LocalDateTime lastLogin;
}
