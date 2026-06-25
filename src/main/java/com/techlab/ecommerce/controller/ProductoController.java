package com.techlab.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController // le dice a Spring que esta clase va a manejar requests HTTP y que las respuestas se van a serializar automáticamente a JSON. Sin esta anotación, Spring no sabe que esta clase es un controlador.
@RequestMapping("/productos") // define la URL base. Todo endpoint de esta clase va a empezar con /productos 
public class ProductoController {

    private final ProductoService service; // Inyección por constructor — ProductoService no se crea con new. Spring lo crea automáticamente y se lo pasa al controlador.

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping // mapea el método listarTodos() a una request HTTP GET. Cuando alguien haga GET /productos, Spring ejecuta ese método.
    public List<Producto> listar() {
        return service.listar();
    }


    @GetMapping("/{id}")
    public Producto obtenerProducto (@PathVariable int id) {
        return service.buscarPorId(id);
    }


    @PostMapping("")
    public Producto crearProducto(@RequestBody Producto producto){
      return service.agregar(producto);
    }
    

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable int id, @RequestBody Producto datos) {
      return service.modificar(id, datos);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
      service.eliminar(id);
    }
}