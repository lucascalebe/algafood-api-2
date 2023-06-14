package com.algaworks.algafoodapi2.domain.service;

import com.algaworks.algafoodapi2.domain.exception.EntityInUseException;
import com.algaworks.algafoodapi2.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi2.domain.model.Kitchen;
import com.algaworks.algafoodapi2.domain.model.Restaurant;
import com.algaworks.algafoodapi2.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi2.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;
  private final KitchenRepository kitchenRepository;

  public Restaurant save(Restaurant restaurant) {
    Long kitchenId = restaurant.getKitchen().getId();

    var kitchen = kitchenRepository.findById(kitchenId);

    if (kitchen.isEmpty()) {
      throw new EntityNotFoundException(String.format("Kitchen with id %d was not found", kitchenId));
    }

    restaurant.setKitchen(kitchen.get());

    return restaurantRepository.save(restaurant);
  }

  public void delete(Long restaurantId) {
    try {
      restaurantRepository.deleteById(restaurantId);

    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format("Restaurant with id %d was not found", restaurantId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format("Restaurant with id %d can't be remove, because it's in use.", restaurantId));
    }
  }
}
