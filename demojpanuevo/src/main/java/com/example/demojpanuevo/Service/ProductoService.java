package com.example.demojpanuevo.Service;

import com.example.demojpanuevo.model.ProductoModel;
import com.example.demojpanuevo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    // crear producto
    public ProductoModel createProduct(ProductoModel newProduct){
        return this.productoRepository.save(newProduct);
    }
    // buscar producto por ID
    public ProductoModel findById(Integer id){
        // en la variable productFound se guarda el producto encontrado
        Optional<ProductoModel> productFound = this.productoRepository.findById(id);
        // retorno el producto encontrado o null
        return productFound.orElse(null);
    }

    // buscar producto por codigo
    public ProductoModel findByCode(String code){
        Optional<ProductoModel> productFound = this.productoRepository.findByCode(code);
        // retorno el producto encontrado o null
        return productFound.orElse(null);
    }

    // obtener la lista de todos los productos
    public List<ProductoModel> findAll(){
        return this.productoRepository.findAll();
    }

    // modificar producto
    public ProductoModel update(ProductoModel newProduct, Integer id){
        Optional<ProductoModel> productDB = this.productoRepository.findById(id);

        if(productDB.isEmpty()){
            return null;
        }
        ProductoModel updatedProduct = productDB.get();
        updatedProduct.setDescripcion( newProduct.getDescripcion() );
        updatedProduct.setCodigo( newProduct.getCodigo() );
        updatedProduct.setStock( newProduct.getStock() );
        updatedProduct.setPrecio( newProduct.getPrecio() );
        return this.productoRepository.save(updatedProduct);
    }
    // eliminar un producto
    public void delete(Integer id){
        this.productoRepository.deleteById(id);
    }
    // actualizar stock
    public void updateStock(Integer id, int quantitySold) {
        Optional<ProductoModel> productFound = productoRepository.findById(id);

        if (productFound.isPresent()) {
            ProductoModel product = productFound.get();
            int currentStock = product.getStock();
            int updatedStock = currentStock - quantitySold;

            if (updatedStock >= 0) {
                product.setStock(updatedStock);
                productoRepository.save(product);
            } else {
                throw new IllegalArgumentException("No hay suficiente stock para realizar la venta.");
            }
        }else {
            throw new IllegalArgumentException("Producto no encontrado con el ID proporcionado: " + id);
        }
    }
}
