package com.algaworks.algafoodapi2.domain.repository;

import com.algaworks.algafoodapi2.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
