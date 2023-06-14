package com.algaworks.algafoodapi2.domain.service;

import com.algaworks.algafoodapi2.domain.model.Kitchen;
import com.algaworks.algafoodapi2.domain.repository.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenService {

  private final KitchenRepository kitchenRepository;

  public Kitchen save(Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }
}
