package com.example.demojpanuevo.Service;

import com.example.demojpanuevo.model.ClienteModel;
import com.example.demojpanuevo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    // crear nuevo cliente
    public ClienteModel createClient(ClienteModel newClient){
        return this.clienteRepository.save(newClient);
    }

    // buscar cliente por ID
    public ClienteModel findById(Integer id){
        Optional<ClienteModel> client = this.clienteRepository.findById(id);
        return client.orElse(null);
    }
    // buscar cliente por número de documento
    public ClienteModel findByDocNumber(String docNumber){
        Optional<ClienteModel> client = this.clienteRepository.findByDocNumber(docNumber);
        return client.orElse(null);
    }
    // obtener la lista de todos los clientes
    public List<ClienteModel> findAll(){
        return this.clienteRepository.findAll();
    }
    // modificar cliente
    public ClienteModel update(ClienteModel newClient, Integer id){
        Optional<ClienteModel> clientDB = this.clienteRepository.findById(id);

        if(clientDB.isEmpty()){
            return null;
        }
        ClienteModel updateClient = clientDB.get();
        updateClient.setNombre( newClient.getNombre() );
        updateClient.setApellido( newClient.getApellido() );
        updateClient.setNumeroDocumento( newClient.getNumeroDocumento() );
        return this.clienteRepository.save(updateClient);
    }
    // eliminar un cliente
    public void delete(Integer id){
        this.clienteRepository.deleteById(id);
    }

}
