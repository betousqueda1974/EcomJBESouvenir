package com.techlab.ecommerce.model;

public class Producto {

  // Contador compartido por todos los objetos. Genera id únicos para cada Producto que se va creando.
  private static int contadorId = 1;

  private int id;
  private String nombre;
  private double precio;
  private int stock;

  public Producto(String nombre, double precio, int stock) {
    this.id = contadorId++;
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
  }

  // Getters
  public int getId() { return id; }
  public String getNombre() { return nombre; }
  public double getPrecio() { return precio; }
  public int getStock() { return stock; }

  // Setters
  public void setNombre(String nombre) { this.nombre = nombre; }
  public void setPrecio(double precio) { this.precio = precio; }
  public void setStock(int stock) { this.stock = stock; }


  //Se llama automáticamente cuando hacemos System.out.println(producto).
  @Override
    public String toString() {
      return "[ID: " + id + "] " + nombre +
            " | Precio: $" + precio +
            " | Stock: " + stock;
    }
}