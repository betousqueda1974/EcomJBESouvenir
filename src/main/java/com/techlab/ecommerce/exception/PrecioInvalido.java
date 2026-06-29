package com.techlab.ecommerce.exception;

public class PrecioInvalido extends RuntimeException {
  public PrecioInvalido(String mensaje) {
    super(mensaje);
  }
}