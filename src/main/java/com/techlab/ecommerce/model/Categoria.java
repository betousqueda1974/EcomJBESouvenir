package com.techlab.ecommerce.model;

import org.hibernate.annotations.Collate;
import org.hibernate.annotations.Columns;

import jakarta.persistence.*;

/*
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 */

// [REPASO] Nuevo modelo: representa una categoría del catálogo.
// En el proyecto final, cada Producto pertenece a una Categoria.

@Entity
@Table(name = "Categoria")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "nombre", nullable = false, length = 50)
  private String nombre;
  @Column(name = "descripcion", length = 200)
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