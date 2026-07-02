package com.techlab.ecommerce.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techlab.ecommerce.exception.ProductoNoEncontrado;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;

@RestController // le dice a Spring que esta clase va a manejar requests HTTP y que las respuestas se van a serializar automáticamente a JSON. Sin esta anotación, Spring no sabe que esta clase es un controlador.
@RequestMapping("/productos") // define la URL base. Todo endpoint de esta clase va a empezar con /productos 
public class ProductoController {

  private final ProductoService service; // Inyección por constructor — ProductoService no se crea con new. Spring lo crea automáticamente y se lo pasa al controlador.

  public ProductoController(ProductoService service) {
      this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Producto>> listar() {
    List<Producto> productos = service.listar();
    return ResponseEntity.ok(productos);
  }

  //PathVariable toma el valor que viene como parámetro en el GetMapping.
  @GetMapping("/{id}")
  public ResponseEntity<Producto> obtenerProducto(@PathVariable int id) {
    try {
      Producto producto = service.buscarPorId(id);
        return ResponseEntity.ok(producto);
    } catch (ProductoNoEncontrado e) {
        return ResponseEntity.notFound().build();
    }
  }

  // @RequestBody convierte el JSON del body en un objeto Producto
  @PostMapping("")
  public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {
    Producto creado = service.agregar(nuevoProducto);
    return ResponseEntity.status(HttpStatus.CREATED).body(creado);
  }
  
  // @PathVariable toma el ID de la URL
  // @RequestBody convierte el JSON del body con los nuevos datos
  // Si existe: 200 OK con el producto actualizado
  // Si no existe: 404 Not Found
  @PutMapping("/{id}")
  public ResponseEntity<Producto> actualizar(@PathVariable int id, @RequestBody Producto datos) {
    try {
      Producto actualizado = service.modificar(id, datos);
      return ResponseEntity.ok(actualizado);
    } catch (ProductoNoEncontrado e) {
        return ResponseEntity.notFound().build();
    }
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable int id) {
    try {
      service.eliminar(id);
      return ResponseEntity.ok().build();
    } catch (ProductoNoEncontrado e) {
      return ResponseEntity.notFound().build();
    }
  }

}