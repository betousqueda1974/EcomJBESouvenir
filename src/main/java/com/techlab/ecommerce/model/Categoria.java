package com.techlab.ecommerce.model;

// [REPASO] Nuevo modelo: representa una categoría del catálogo.
// En el proyecto final, cada Producto pertenece a una Categoria.
public class Categoria {

  private int id;
  private String nombre;
  private String descripcion;

  public Categoria(String nombre, String descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  // Constructor vacío: lo necesita Spring/JPA.
  public Categoria() {}

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getDescripcion() { return descripcion; }
  public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

  @Override
  public String toString() {
    return "ID: " + id + " | " + nombre + " | " + descripcion;
  }
}