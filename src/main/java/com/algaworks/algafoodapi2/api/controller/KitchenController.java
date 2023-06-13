package com.algaworks.algafoodapi2.api.controller;

import com.algaworks.algafoodapi2.domain.model.Kitchen;
import com.algaworks.algafoodapi2.domain.repository.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  public ResponseEntity<Kitchen> getKitchen(@PathVariable Long kitchenId) {
    var kitchen = kitchenRepository.findById(kitchenId);

    if (kitchen.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(kitchen.get());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Kitchen addKitchen(@RequestBody Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }
}