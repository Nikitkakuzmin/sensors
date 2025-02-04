package kz.nik.sensorspostgswagkeycloack.service;


import kz.nik.sensorspostgswagkeycloack.dto.MeasurementDTO;
import kz.nik.sensorspostgswagkeycloack.model.Measurement;
import kz.nik.sensorspostgswagkeycloack.model.Sensor;
import kz.nik.sensorspostgswagkeycloack.repository.MeasurementRepository;
import kz.nik.sensorspostgswagkeycloack.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final Random random = new Random();

    @Transactional
    public Measurement addMeasurement(MeasurementDTO measurementDTO) {
        Sensor sensor = sensorRepository.findByName(measurementDTO.getSensor().getName())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        Measurement measurement = new Measurement();
        measurement.setValue(measurementDTO.getValue());
        measurement.setRaining(measurementDTO.getRaining());
        measurement.setSensor(sensor);

        return measurementRepository.save(measurement);
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public long getRainyDaysCount() {
        return measurementRepository.countByRainingTrue();
    }

    @Transactional
    public void addRandomMeasurements(String sensorName, int count) {
        Sensor sensor = sensorRepository.findByName(sensorName)
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        for (int i = 0; i < count; i++) {
            Measurement measurement = new Measurement();
            measurement.setValue(random.nextDouble() * 50);
            measurement.setRaining(random.nextBoolean());
            measurement.setSensor(sensor);
            measurementRepository.save(measurement);
        }
    }
}