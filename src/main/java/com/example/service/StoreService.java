package com.example.service;

import com.example.model.dto.StoreDTO;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    void createAll(List<StoreDTO> storeDTOs);

    List<StoreDTO> findAll();

    Optional<StoreDTO> findById(String id);
}
