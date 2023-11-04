package com.projects.PassGuard.dto;

import com.projects.PassGuard.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatformDto {

    private Long id;
    private String name;
    private String iconUri;
    private String createdAt;
    private String lastModification;
    private List<Account> accounts;

}
