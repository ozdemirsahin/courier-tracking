package com.example.data.repository;

import com.example.data.entity.Courier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierRepository extends CrudRepository<Courier, String> {

    Optional<Courier> findCourierByPersonalNo(String personalNo);

}

