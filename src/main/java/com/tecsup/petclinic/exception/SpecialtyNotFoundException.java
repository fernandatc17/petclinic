package com.tecsup.petclinic.exception;

/**
 * Excepción personalizada para cuando una especialidad no es encontrada.
 *
 */
public class SpecialtyNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public SpecialtyNotFoundException(String message) {
    super(message);
  }
}

