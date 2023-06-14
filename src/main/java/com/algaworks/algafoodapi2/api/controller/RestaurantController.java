package com.algaworks.algafoodapi2.api.controller;

import com.algaworks.algafoodapi2.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi2.domain.model.Restaurant;
import com.algaworks.algafoodapi2.domain.repository.RestaurantRepository;
import com.algaworks.algafoodapi2.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {

  private final RestaurantRepository restaurantRepository;
  private final RestaurantService restaurantService;

  @GetMapping
  public List<Restaurant> restaurantList() {
    return restaurantRepository.findAll();
  }

  @GetMapping("/{restaurantId}")
  public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long restaurantId) {
    var restaurant = restaurantRepository.findById(restaurantId);

    if (restaurant.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(restaurant.get());
  }

  @PostMapping
  public ResponseEntity<Object> addRestaurant(@RequestBody Restaurant restaurant) {
    try {
      Restaurant restaurantSaved = restaurantService.save(restaurant);

      return ResponseEntity.status(HttpStatus.CREATED)
              .body(restaurantSaved);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest()
              .body(e.getMessage());
    }
  }
}
