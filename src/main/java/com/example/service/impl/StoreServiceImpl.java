package com.example.service.impl;

import com.example.data.entity.Store;
import com.example.data.repository.StoreRepository;
import com.example.model.converter.StoreConverter;
import com.example.model.dto.StoreDTO;
import com.example.service.StoreService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    private final StoreConverter storeConverter;

    @Override
    public void createAll(List<StoreDTO> storeDTOs) {
        List<Store> stores = storeDTOs.stream().map(storeConverter::toEntity).collect(Collectors.toList());
        storeRepository.saveAll(stores);
    }

    @Override
    public List<StoreDTO> findAll() {
        Iterable<Store> allStoresIterable = storeRepository.findAll();
        List<Store> allStores = Lists.newArrayList(allStoresIterable);
        if(CollectionUtils.isEmpty(allStores)){
            return Lists.newArrayList();
        }
        return allStores.stream().map(storeConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<StoreDTO> findById(String id) {
        return storeRepository.findById(id).map(storeConverter::toDTO);
    }
}
