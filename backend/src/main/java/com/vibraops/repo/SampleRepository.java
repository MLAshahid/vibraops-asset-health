package com.vibraops.repo;

import com.vibraops.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;
import java.util.List;

/**
 * Repository interface for {@link Sample} entities.
 */
public interface SampleRepository extends JpaRepository<Sample, Long> {
    @Query("SELECT s FROM Sample s WHERE s.device.id = :deviceId AND s.ts >= :from AND s.ts <= :to ORDER BY s.ts DESC")
    List<Sample> findSamples(@Param("deviceId") Long deviceId, @Param("from") Instant from, @Param("to") Instant to);
}