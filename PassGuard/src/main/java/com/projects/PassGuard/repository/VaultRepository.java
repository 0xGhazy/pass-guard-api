package com.projects.PassGuard.repository;

import com.projects.PassGuard.domain.Vault;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface VaultRepository extends JpaRepository<Vault, Long> {
    Optional<Vault> findVaultByName(String name);
    ArrayList<Vault> findAllByIdIsNotNull();
}
