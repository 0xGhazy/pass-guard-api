package com.projects.PassGuard.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "Valuts")
public class Vault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @JsonIgnore
    private String createdAt;
    @JsonIgnore
    private String lastModification;
    @JsonIgnore
    @OneToMany(mappedBy = "vault")
    private List<Account> accounts;
}
