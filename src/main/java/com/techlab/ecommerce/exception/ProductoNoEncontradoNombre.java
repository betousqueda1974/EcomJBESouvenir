package com.techlab.ecommerce.exception;

public class ProductoNoEncontradoNombre extends RuntimeException {

  public ProductoNoEncontradoNombre(String nombre) {
    super("No se encontró ningún producto con el nombre: " + nombre);
  }
}