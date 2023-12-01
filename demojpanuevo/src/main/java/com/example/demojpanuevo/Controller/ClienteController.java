package com.example.demojpanuevo.Controller;

import com.example.demojpanuevo.Service.ClienteService;
import com.example.demojpanuevo.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping(path = "api/client")
@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //crear cliente
    @PostMapping("/")
    public ResponseEntity<ClienteModel> createClient(@RequestBody ClienteModel client){
        return new ResponseEntity<>(this.clienteService.createClient(client), HttpStatus.CREATED);
    }
    //buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> findById(@PathVariable Integer id){
        // en la variable clientFound se guarda el cliente encontrado
        ClienteModel clientFound = clienteService.findById(id);
        // en caso de ser nulo
        if (clientFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado. El número de id "+id+" no existe.");
        }
        // retorno el cliente encontrado
        return new ResponseEntity<>(clientFound, HttpStatus.OK);
    }
    // buscar cliente por número de documento
    @GetMapping("/doc/{docNumber}")
    public ResponseEntity<ClienteModel> findByDocNumber(@PathVariable String docNumber) {
        // en la variable clientFound se guarda el cliente encontrado
        ClienteModel clientFound = clienteService.findByDocNumber(docNumber);
        //en caso de ser nulo
        if (clientFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado. El número de documento "+docNumber+" no existe.");
        }
        // retorno el cliente encontrado
        return new ResponseEntity<>(clientFound, HttpStatus.OK);
    }
    // obtener la lista de todos los clientes directamente de (path = "api/client")
    @GetMapping
    public ResponseEntity<List<ClienteModel>> findAll(){
        return new ResponseEntity<>(this.clienteService.findAll(), HttpStatus.OK);
    }
    // editar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> update(@RequestBody ClienteModel clientUpdate, @PathVariable Integer id) {
        // en la variable clientFound se guarda el cliente encontrado
        ClienteModel clientFound = clienteService.update(clientUpdate, id);
        //en caso de ser null
        if (clientFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado. El número de id "+id+" no existe.");
        }
        // retorno el cliente encontrado
        return new ResponseEntity<>(clientFound, HttpStatus.OK);
    }
    // elimnar un cliente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        ClienteModel clientFound = clienteService.findById(id);
        if (clientFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado. El número de id "+id+" no existe.");
        }
        this.clienteService.delete(id);
        return new ResponseEntity<>("Cliente eliminado correctamente.", HttpStatus.OK);
    }

}
