package com.example.demojpanuevo.repository;

import com.example.demojpanuevo.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<ProductoModel,Integer> {
    Optional<ProductoModel> findByCode(String code);
}
