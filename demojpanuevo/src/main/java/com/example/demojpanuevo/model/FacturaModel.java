package com.example.demojpanuevo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "factura")
public class FacturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private ClienteModel cliente;
    @Column(name="fecha_creacion")
    private LocalDate fechaCreacion;
    private double total;
    @OneToMany(mappedBy = "invoice")
    @JsonManagedReference
    private List<DetallesFacturaModel> detallesFactura;
}
