package com.algaworks.algafoodapi2.api.controller;

import com.algaworks.algafoodapi2.domain.exception.EntityInUseException;
import com.algaworks.algafoodapi2.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi2.domain.model.Kitchen;
import com.algaworks.algafoodapi2.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi2.domain.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  private final KitchenService kitchenService;

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
    return kitchenService.save(kitchen);
  }

  @PutMapping("/{kitchenId}")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Kitchen> updateKitchen(@PathVariable Long kitchenId, @RequestBody Kitchen kitchenUpdated) {
    var kitchenOpt = kitchenRepository.findById(kitchenId);

    if (kitchenOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Kitchen kitchen = kitchenOpt.get();
    BeanUtils.copyProperties(kitchenUpdated, kitchen, "id");
    kitchenService.save(kitchen);

    return ResponseEntity.ok(kitchen);
  }

  @DeleteMapping("/{kitchenId}")
  public ResponseEntity<Kitchen> remove(@PathVariable Long kitchenId) {
    try {
      kitchenService.delete(kitchenId);
      return ResponseEntity.noContent().build();

    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();

    } catch (EntityInUseException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
