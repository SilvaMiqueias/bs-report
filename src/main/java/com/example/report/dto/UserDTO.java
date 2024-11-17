package com.example.report.dto;

import com.example.report.model.enumerador.RoleName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String name;
    RoleName role;
}
