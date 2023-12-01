package com.example.demojpanuevo.Controller;

import com.example.demojpanuevo.Service.FacturaService;
import com.example.demojpanuevo.model.FacturaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping(path = "api/invoice")
@RestController
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    //crear factura
    @PostMapping("/")
    public ResponseEntity<FacturaModel> createInvoice(@RequestBody FacturaModel invoice){
        return new ResponseEntity<>(this.facturaService.createInvoice(invoice), HttpStatus.CREATED);
    }
    // obtener factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<FacturaModel> findInvoiceById(@PathVariable Integer id) {
        FacturaModel invoice = facturaService.findInvoiceById(id);
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Factura no encontrada. El número de id "+id+" no existe.");
        }
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

}
