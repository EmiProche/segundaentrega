package com.example.demojpanuevo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "clientes")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;

    @Column(name="numnero_documento")
    private String numeroDocumento;
}
