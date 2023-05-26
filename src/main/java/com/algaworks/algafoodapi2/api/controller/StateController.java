package com.algaworks.algafoodapi2.api.controller;

import com.algaworks.algafoodapi2.domain.model.State;
import com.algaworks.algafoodapi2.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/states", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StateController {

  private final StateRepository stateRepository;

  @GetMapping
  public List<State> stateList() {
    return stateRepository.findAll();
  }

  @GetMapping("/{stateId}")
  public State state(@PathVariable Long stateId) {
    return stateRepository.findById(stateId)
            .orElseThrow(() -> new IllegalArgumentException(
                    String.format("State of id %s was not found", stateId))
            );
  }
}
