package com.projects.PassGuard.repository;

import com.projects.PassGuard.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    ArrayList<Platform> findPlatformsByIdNotNull();
}
