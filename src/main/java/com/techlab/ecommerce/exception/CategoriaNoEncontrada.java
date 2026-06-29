package com.techlab.ecommerce.exception;

public class CategoriaNoEncontrada extends RuntimeException {
  public CategoriaNoEncontrada(String mensaje) {
    super(mensaje);
  }
}