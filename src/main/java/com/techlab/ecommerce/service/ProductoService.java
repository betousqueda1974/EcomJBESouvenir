package com.techlab.ecommerce.service;


import com.techlab.ecommerce.model.*;
import com.techlab.ecommerce.exception.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

  // Lista en memoria
  //private ArrayList<Producto> productos = new ArrayList<>();

  private List<Producto> productos = new ArrayList<>();

  //Agregar nuevo producto.
  public void agregar(String nombre, double precio, int stock) {
    Producto nuevo = new Producto(nombre, precio, stock);
    productos.add(nuevo);
    System.out.println("Producto agregado: " + nuevo);
  }

  //Modificar datos de un producto
  public void modificar (int id, String nombre, double precio, int stock) {
    Producto p = buscarPorId(id);
    p.setNombre(nombre);
    p.setPrecio(precio);
    p.setStock(stock);
    System.out.println("Producto modificado: " + p);
  }


  //Listar todos los productos.
  /*public void listar() {
    if (productos.isEmpty()) {
      System.out.println("No hay productos cargados.");
      return;
    }
    System.out.println("\n--- Listado de productos ---");
    for (Producto p : productos) {
      System.out.println(p);
    }
  }
*/

  public List<Producto> listar() {
        return productos;
    }

  //Buscar producto por ID.
  public Producto buscarPorId(int id) {
    for (Producto p : productos) {
      if (p.getId() == id) {
        return p;
      }
    }
    // Si llegamos acá es porque no lo encontramos
    throw new ProductoNoEncontrado(id);
  }

  //Buscar producto por nombre
  public Producto buscarPorNombre(String nombre) {
    for (Producto p : productos) {
      nombre = nombre.toUpperCase().trim();
      if (p.getNombre().toUpperCase().trim().equals(nombre)) {
        return p;
      }
    }
    // Si llegamos acá es porque no lo encontramos
    throw new ProductoNoEncontradoNombre(nombre);
  }

  //Eliminar un producto por ID.
  public void eliminar(int id) {
    Producto p = buscarPorId(id);
    productos.remove(p);
    System.out.println("Producto eliminado: " + p.getNombre());
  }
}