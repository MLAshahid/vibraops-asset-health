package com.vibraops.repo;

import com.vibraops.entity.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;
import java.util.List;

/**
 * Repository interface for {@link Anomaly} entities.
 */
public interface AnomalyRepository extends JpaRepository<Anomaly, Long> {
    @Query("SELECT a FROM Anomaly a WHERE (:deviceId IS NULL OR a.device.id = :deviceId) AND a.ts >= :from AND a.ts <= :to ORDER BY a.ts DESC")
    List<Anomaly> findAnomalies(@Param("deviceId") Long deviceId,
                                @Param("from") Instant from,
                                @Param("to") Instant to);
}