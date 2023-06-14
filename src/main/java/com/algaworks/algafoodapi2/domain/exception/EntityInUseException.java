package com.algaworks.algafoodapi2.domain.exception;

public class EntityInUseException extends RuntimeException {

  public EntityInUseException(String messsage) {
    super(messsage);
  }
}
