package com.vibraops.web;

import com.vibraops.entity.Device;
import com.vibraops.repo.DeviceRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.Instant;
import java.util.List;

/**
 * REST controller for managing devices.  Supports CRUD operations.
 */
@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    private final DeviceRepository repository;
    public DeviceController(DeviceRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Device> all() {
        return repository.findAll();
    }
    @PostMapping
    public ResponseEntity<Device> create(@Valid @RequestBody Device d) {
        d.setLastSeen(Instant.now());
        return ResponseEntity.ok(repository.save(d));
    }
    @GetMapping("/{id}")
    public Device one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public Device update(@PathVariable Long id, @RequestBody Device upd) {
        Device existing = one(id);
        existing.setName(upd.getName());
        existing.setStatus(upd.getStatus());
        existing.setLocation(upd.getLocation());
        existing.setLastSeen(Instant.now());
        return repository.save(existing);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}