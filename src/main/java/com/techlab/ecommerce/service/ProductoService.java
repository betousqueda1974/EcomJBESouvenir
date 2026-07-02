package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.*;
import com.techlab.ecommerce.repository.ProductoRepository;
import com.techlab.ecommerce.exception.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

  //Inyección por constructor. SpringBoot pasa el repositorio
  private final ProductoRepository repository;

  public ProductoService(ProductoRepository repository) {
    this.repository = repository;
  }
  
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
      return repository.save(p);
    }


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

    Producto p = buscarPorId(id);
   
    p.setNombre(datos.getNombre());
    p.setPrecio(datos.getPrecio());
    p.setStock(datos.getStock());
    p.setCategoria(datos.getCategoria());

    return repository.save(p);
    }


  public List<Producto> listar() {
    return repository.findAll();
    }

  //Buscar producto por ID.
  public Producto buscarPorId(int id) {
    return repository.findById(id)
      .orElseThrow(() -> new ProductoNoEncontrado(id));
  }
/*
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
*/
  //Eliminar un producto por ID.
  public void eliminar(int id) {
    Producto p = buscarPorId(id);
    repository.delete(p);
  }
}