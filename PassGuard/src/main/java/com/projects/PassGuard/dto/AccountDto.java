package com.projects.PassGuard.dto;

import com.projects.PassGuard.domain.Platform;
import com.projects.PassGuard.domain.Vault;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String username;
    private String password;
    private String createdAt;
    private String lastModification;
    private Platform platform;
    private Vault vault;

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", lastModification='" + lastModification + '\'' +
                ", platform=" + platform +
                ", vault=" + vault +
                '}';
    }
}
