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

  private static int contadorId = 1;

  /*
  //Agregar nuevo producto.
  public void agregar(String nombre, double precio, int stock) {
    Producto nuevo = new Producto(nombre, precio, stock);
    productos.add(nuevo);
    System.out.println("Producto agregado: " + nuevo);
  }
*/

   // CREATE: agrega un nuevo producto al catálogo.
    public Producto agregar(Producto p) {
      //Validaciones
      if (p.getNombre() == null || p.getNombre().isBlank()) {
        throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
      }
      if (p.getPrecio() <= 0) {
        throw new PrecioInvalido("El precio debe ser mayor a cero. Se recibió: " + p.getPrecio());
      }
      if (p.getStock() < 0) {
        throw new StockInsuficiente("El stock no puede ser negativo. Se recibió: " + p.getStock());
      }

        // El id lo asigna el servicio, no el usuario. Después de
      p.setId(contadorId);    // asignarlo, incrementamos el contador para el próximo.
      contadorId++;
      productos.add(p);
      System.out.println("Producto guardado: " + p); // <-- agrega esto para verificar que el producto se guardó correctamente
      return p;
    }



/*
  //Modificar datos de un producto
  public void modificar (int id, String nombre, double precio, int stock) {
    Producto p = buscarPorId(id);
    p.setNombre(nombre);
    p.setPrecio(precio);
    p.setStock(stock);
    System.out.println("Producto modificado: " + p);
  }

 */

  public Producto modificar(int id, Producto datos) {
    //Validaciones
    if (datos.getNombre() == null || datos.getNombre().isBlank()) {
      throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
    }
    if (datos.getPrecio() <= 0) {
      throw new PrecioInvalido("El precio debe ser mayor a cero. Se recibió: " + datos.getPrecio());
    }
    if (datos.getStock() < 0) {
      throw new StockInsuficiente("El stock no puede ser negativo. Se recibió: " + datos.getStock());
    }
    
    // Reutilizamos obtenerPorId: si no existe, lanza excepción
    // y la actualización se cancela automáticamente.
    Producto p = buscarPorId(id);
    // Modificamos el producto encontrado. Como Java pasa los
    // objetos por referencia, los cambios se reflejan en la
    // lista sin necesidad de hacer nada más.

    p.setNombre(datos.getNombre());
    p.setPrecio(datos.getPrecio());
    p.setStock(datos.getStock());

    return p;
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