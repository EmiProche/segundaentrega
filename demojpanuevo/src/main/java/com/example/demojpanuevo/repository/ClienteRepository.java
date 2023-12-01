package com.example.demojpanuevo.repository;

import com.example.demojpanuevo.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel,Integer>  {
    Optional<ClienteModel> findByDocNumber(String docNumber);
}
