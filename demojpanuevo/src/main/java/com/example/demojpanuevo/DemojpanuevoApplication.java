package com.example.demojpanuevo;

import com.example.demojpanuevo.model.DetallesFacturaModel;
import com.example.demojpanuevo.repository.ClienteRepository;
import com.example.demojpanuevo.repository.DetallesFacturaRepository;
import com.example.demojpanuevo.repository.FacturaRepository;
import com.example.demojpanuevo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemojpanuevoApplication {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	DetallesFacturaRepository detallesFacturaRepository;

	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemojpanuevoApplication.class, args);
	}

}
