package com.example.demojpanuevo.Service;

import com.example.demojpanuevo.model.DetallesFacturaModel;
import com.example.demojpanuevo.model.ProductoModel;
import com.example.demojpanuevo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallesFacturaService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ProductoService productoService;
    public double calculateTotal(List<DetallesFacturaModel> invoiceDetails) {
        double total = 0.0;

        for (DetallesFacturaModel detail : invoiceDetails) {
            // Verifica si el producto existe
            ProductoModel product = productoRepository.findById(detail.getProducto().getId()).orElse(null);
            if (product == null) {
                throw new IllegalArgumentException("El producto no existe.");
            }
            // Verifica si hay suficiente stock
            if (detail.getQuantity() > product.getStock()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + product.getDescripcion());
            }
            // Actualiza el stock del producto
            productoService.updateStock(product.getId(), detail.getQuantity());
            // Calcula el subtotal del detalle
            double subtotal = detail.getImporte() * detail.getQuantity();
            // Acumula al total de la factura
            total += subtotal;
        }
        return total;
    }
}
