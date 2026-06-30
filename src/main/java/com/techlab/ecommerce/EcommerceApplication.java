package com.techlab.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

/*
	@Bean
    public CommandLineRunner cargarDatos(ProductoService service) {
        return args -> {
						service.agregar(new Producto ("Café molido 500g", 4500, 30));
						service.agregar(new Producto ("Yerba mate 1kg", 3200, 50));
						service.agregar(new Producto ("Galletitas dulces", 1850, 100));
        };
    }
 */


		// Carga datos iniciales SOLO si la base está vacía.
    // Así evitamos duplicar los productos en cada reinicio.
  @Bean
  CommandLineRunner cargarDatos(ProductoService productoService) {
    return args -> {
      if (productoService.listar().isEmpty()) {
          productoService.agregar(new Producto("Yerba 1kg", 3200, 50, "Almacén"));
          productoService.agregar(new Producto("Aceite 1.5L", 4100, 30, "Almacén"));
          productoService.agregar(new Producto("Fideos 500g", 1500, 80, "Almacén"));
      }
    };
  }

}
