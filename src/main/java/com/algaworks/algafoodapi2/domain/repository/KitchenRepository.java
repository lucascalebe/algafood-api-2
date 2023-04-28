package com.algaworks.algafoodapi2.domain.repository;

import com.algaworks.algafoodapi2.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
}
