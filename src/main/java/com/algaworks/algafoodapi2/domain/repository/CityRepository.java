package com.algaworks.algafoodapi2.domain.repository;

import com.algaworks.algafoodapi2.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
