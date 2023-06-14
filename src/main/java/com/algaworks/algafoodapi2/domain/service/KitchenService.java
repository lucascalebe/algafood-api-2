package com.algaworks.algafoodapi2.domain.service;

import com.algaworks.algafoodapi2.domain.exception.EntityInUseException;
import com.algaworks.algafoodapi2.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi2.domain.model.Kitchen;
import com.algaworks.algafoodapi2.domain.repository.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenService {

  private final KitchenRepository kitchenRepository;

  public Kitchen save(Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }

  public void delete(Long kitchenId) {
    try {
      kitchenRepository.deleteById(kitchenId);

    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format("Kitchen with id: %d was not found", kitchenId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format("Kitchen with id: %d can't be remove, because it's in use.", kitchenId));
    }
  }
}
