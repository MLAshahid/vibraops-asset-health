package com.vibraops.repo;

import com.vibraops.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Device} entities.
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {
}