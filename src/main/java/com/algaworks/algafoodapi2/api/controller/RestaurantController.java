package com.algaworks.algafoodapi2.api.controller;

import com.algaworks.algafoodapi2.domain.model.Restaurant;
import com.algaworks.algafoodapi2.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {

  private final RestaurantRepository restaurantRepository;

  @GetMapping
  public List<Restaurant> restaurantList() {
    return restaurantRepository.findAll();
  }

  @GetMapping("/{restaurantId}")
  public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long restaurantId) {
    var kitchen = restaurantRepository.findById(restaurantId);

    if (kitchen.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(kitchen.get());
  }
}
