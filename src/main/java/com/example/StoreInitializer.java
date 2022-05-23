package com.example;

import com.example.model.dto.StoreDTO;
import com.example.service.StoreService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class StoreInitializer {

    private final StoreService storeService;

    @PostConstruct
    private void initDb() {
        createStores();
    }

    private void createStores() {
        StoreDTO ataSehir = StoreDTO.builder().id(UUID.randomUUID().toString()).lat(40.9923307).lng(29.1244229).name("Ataşehir MMM Migros").city("Ataşehir").build();
        StoreDTO novada = StoreDTO.builder().id(UUID.randomUUID().toString()).lat(40.986106).lng(29.1161293).name("Novada MMM Migros").city("Novada").build();
        StoreDTO beylikDuzu = StoreDTO.builder().id(UUID.randomUUID().toString()).lat(41.0066851).lng(28.6552262).name("Beylikdüzü 5M Migros").city("Beylikdüzü").build();
        StoreDTO ortaKoy = StoreDTO.builder().id(UUID.randomUUID().toString()).lat(41.055783).lng(29.0210292).name("Ortaköy MMM Migros").city("Ortaköy").build();
        StoreDTO caddeBostan = StoreDTO.builder().id(UUID.randomUUID().toString()).lat(40.9632463).lng(29.0630908).name("Caddebostan MMM Migros").city("Caddebostan").build();
        storeService.createAll(Lists.newArrayList(ataSehir, novada, beylikDuzu, ortaKoy, caddeBostan));

        log.info("all stores are initilized");
    }
}
