package com.algaworks.algafoodapi2.domain.repository;

import com.algaworks.algafoodapi2.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
