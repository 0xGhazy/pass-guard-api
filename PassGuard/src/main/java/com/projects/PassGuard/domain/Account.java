package com.projects.PassGuard.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "platform_id", "vault_id"})
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;
    @JsonIgnore
    private String createdAt;
    @JsonIgnore
    private String lastModification;
    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;
    @ManyToOne
    @JoinColumn(name = "vault_id")
    private Vault vault;

}
