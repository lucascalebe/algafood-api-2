package com.algaworks.algafoodapi2.api.controller;

import com.algaworks.algafoodapi2.domain.model.Kitchen;
import com.algaworks.algafoodapi2.domain.repository.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class KitchenController {

  private final KitchenRepository kitchenRepository;

  @GetMapping
  public List<Kitchen> kitchenList() {
    return kitchenRepository.findAll();
  }

  @GetMapping("/{kitchenId}")
  public Kitchen kitchen(@PathVariable Long kitchenId) {
    return kitchenRepository.findById(kitchenId)
            .orElseThrow(() -> new IllegalArgumentException(
                    String.format("kitchen of id %s was not found", kitchenId))
            );
  }
}
