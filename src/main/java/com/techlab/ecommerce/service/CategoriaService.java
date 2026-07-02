package com.techlab.ecommerce.service;

import java.util.List;
import com.techlab.ecommerce.exception.*;
import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

  //Inyección por constructor. SpringBoot pasa el repositorio
  private final CategoriaRepository repository;

  public CategoriaService(CategoriaRepository repository) {
    this.repository = repository;
  }

  public Categoria agregar(Categoria c) {
    if (c.getNombre() == null || c.getNombre().isBlank()) {
      throw new CategoriaNombreInvalido("El nombre de la categoría no puede estar vacío.");
    }
    return repository.save(c);
  }

  public List<Categoria> listar() {
    return repository.findAll();
  }

  public Categoria buscarPorId(int id) {
    return repository.findById(id)
    .orElseThrow(() -> new CategoriaNoEncontrada ("La categoría " + id + " no fue encontrada."));
  }

  public Categoria modificar(int id, Categoria datos) {
    if (datos.getNombre() == null || datos.getNombre().isBlank()) {
      throw new CategoriaNombreInvalido("El nombre de la categoría no puede estar vacío.");
    }
    Categoria c = buscarPorId(id);
    c.setNombre(datos.getNombre());
    c.setDescripcion(datos.getDescripcion());

    return repository.save(c);
  }

  public void eliminar(int id) {
    Categoria c = buscarPorId(id);
    repository.delete(c);
  }
}