package kz.nik.sensorspostgswagkeycloack.service;

import kz.nik.sensorspostgswagkeycloack.dto.SensorDTO;
import kz.nik.sensorspostgswagkeycloack.exceptions.SensorAlreadyExistsException;
import kz.nik.sensorspostgswagkeycloack.model.Sensor;
import kz.nik.sensorspostgswagkeycloack.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    @Transactional
    public Sensor registerSensor(SensorDTO sensorDTO) {
        log.info("Registering sensor: {}", sensorDTO.getName());

        if (sensorDTO.getName() == null || sensorDTO.getName().trim().isEmpty()) {
            log.error("Sensor name is empty");
            throw new IllegalArgumentException("Sensor name cannot be empty");
        }

        if (sensorRepository.findByName(sensorDTO.getName()).isPresent()) {
            log.error("Sensor '{}' already exists", sensorDTO.getName());
            throw new SensorAlreadyExistsException("Sensor with name '" + sensorDTO.getName() + "' already exists");
        }

        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());

        Sensor savedSensor = sensorRepository.save(sensor);
        log.info("Sensor '{}' registered successfully", savedSensor.getName());
        return savedSensor;
    }
}