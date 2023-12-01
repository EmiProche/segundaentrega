package com.example.demojpanuevo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_factura")
public class DetallesFacturaModel {

    private Integer id;
    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private FacturaModel factura;
    @Column(name = "cantidad_productos")
    private int cantidadProductos;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "productos_id", nullable = false)
    private ProductoModel producto;
    private double importe;

    public void setFactura(FacturaModel newInvoice) {
    }


}
