package com.example.demojpanuevo.Controller;

import com.example.demojpanuevo.Service.ProductoService;
import com.example.demojpanuevo.model.ProductoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping(path = "api/product")
@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // crear producto
    @PostMapping("/")
    public ResponseEntity<ProductoModel> createProduct(@RequestBody ProductoModel product){
        return new ResponseEntity<>(this.productoService.createProduct(product), HttpStatus.CREATED);
    }
    // buscar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> findById(@PathVariable Integer id){
        // en la variable productFound se guarda el producto encontrado
        ProductoModel productFound = productoService.findById(id);
        //en caso de ser null
        if (productFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado. El número de id "+id+" no existe.");
        }
        // retorno el producto encontrado
        return new ResponseEntity<>(productFound, HttpStatus.OK);
    }
    // buscar producto por codigo
    @GetMapping("/code/{code}")
    public ResponseEntity<ProductoModel> findByCode(@PathVariable String code) {
        // en la variable productFound se guarda el productp encontrado
        ProductoModel productFound = productoService.findByCode(code);
        //en caso de ser null
        if (productFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado. El código "+code+" no está relacionado con ningún producto.");
        }
        // retorno el producto encontrado
        return new ResponseEntity<>(productFound, HttpStatus.OK);
    }
    // obtener la lista de todos los productos directamente de (path = "api/product")
    @GetMapping
    public ResponseEntity<List<ProductoModel>> findAll(){
        return new ResponseEntity<>(this.productoService.findAll(), HttpStatus.OK);
    }
    // editar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> update(@RequestBody ProductoModel productUpdate, @PathVariable Integer id) {
        // en la variable productFound se guarda el producto encontrado
        ProductoModel productFound = productoService.update(productUpdate, id);
        //en caso de ser null
        if (productFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado. El número de id "+id+" no existe.");
        }
        // retorno el producto encontrado
        return new ResponseEntity<>(productFound, HttpStatus.OK);
    }
    // elimnar un producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        ProductoModel productFound = productoService.findById(id);
        if (productFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado. El número de id "+id+" no existe.");
        }
        this.productoService.delete(id);
        return new ResponseEntity<>("Producto eliminado correctamente.", HttpStatus.OK);
    }
}
