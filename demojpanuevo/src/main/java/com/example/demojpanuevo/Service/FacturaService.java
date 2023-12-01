package com.example.demojpanuevo.Service;

import com.example.demojpanuevo.model.ClienteModel;
import com.example.demojpanuevo.model.DetallesFacturaModel;
import com.example.demojpanuevo.model.FacturaModel;
import com.example.demojpanuevo.repository.ClienteRepository;
import com.example.demojpanuevo.repository.DetallesFacturaRepository;
import com.example.demojpanuevo.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FacturaService {
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    DetallesFacturaService detallesFacturaService;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DetallesFacturaRepository detallesFacturaRepository;

    //crear factura
    public FacturaModel createInvoice(FacturaModel newInvoice){
        // Verifica si el cliente existe
        ClienteModel invoiceClient = newInvoice.getCliente();
        if (invoiceClient == null || invoiceClient.getId() == null) {
            throw new IllegalArgumentException("El cliente en la factura es nulo o no tiene un ID válido.");
        }
        Optional<ClienteModel> client = clienteRepository.findById(invoiceClient.getId());
        if (client.isEmpty()) {
            throw new IllegalArgumentException("El cliente no existe.");
        }
        // se asigna el cliente a la factura
        newInvoice.setCliente(client.get());
        // se asigna la fecha al momento de creación
        newInvoice.setFechaCreacion(LocalDate.now());

        // se estable relación antes de guardar la factura completa
        for (DetallesFacturaModel detail : newInvoice.getDetallesFactura()) {
            detail.setFactura(newInvoice);
        }
        // el total de la factura
        double total = detallesFacturaService.calculateTotal(newInvoice.getDetallesFactura());
        newInvoice.setTotal(total);

        // se guarda la factura en la base de datos
        FacturaModel savedInvoice = facturaRepository.save(newInvoice);

        // se guardan los detalles de la factura después de haber guardado la factura completa
        for (DetallesFacturaModel detail : newInvoice.getDetallesFactura()) {
            detail.setFactura(savedInvoice);
            detallesFacturaRepository.save(detail);
        }
        return savedInvoice;
    }
    // obtener factura por ID
    public FacturaModel findInvoiceById(Integer id) {
        return facturaRepository.findById(id).orElse(null);
    }

}
