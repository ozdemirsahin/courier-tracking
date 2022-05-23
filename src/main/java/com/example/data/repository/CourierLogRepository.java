package com.example.data.repository;

import com.example.data.entity.CourierLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourierLogRepository extends CrudRepository<CourierLog, String> {

    Optional<CourierLog> findCourierLogByLastMovingTimeAfterAndCourierIdEqualsAndStoreIdEquals(LocalDateTime date, String courierId, String storeId);

    List<CourierLog> findCourierLogsByCourierIdOrderByLastMovingTimeDesc(String courierId);

}
