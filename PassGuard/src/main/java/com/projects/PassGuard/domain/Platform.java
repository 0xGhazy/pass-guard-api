package com.projects.PassGuard.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Platforms")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String iconUri;
    @JsonIgnore
    private String createdAt;
    @JsonIgnore
    private String lastModification;
    @JsonIgnore
    @OneToMany(mappedBy = "platform")
    private List<Account> accounts;
}
