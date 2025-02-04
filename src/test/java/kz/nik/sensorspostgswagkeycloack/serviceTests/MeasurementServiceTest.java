package kz.nik.sensorspostgswagkeycloack.serviceTests;

import kz.nik.sensorspostgswagkeycloack.dto.MeasurementDTO;
import kz.nik.sensorspostgswagkeycloack.dto.SensorDTO;
import kz.nik.sensorspostgswagkeycloack.model.Measurement;
import kz.nik.sensorspostgswagkeycloack.model.Sensor;
import kz.nik.sensorspostgswagkeycloack.repository.MeasurementRepository;
import kz.nik.sensorspostgswagkeycloack.repository.SensorRepository;
import kz.nik.sensorspostgswagkeycloack.service.MeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MeasurementServiceTest {

    @Mock
    private MeasurementRepository measurementRepository;

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private MeasurementService measurementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMeasurement() {
        Sensor sensor = new Sensor(1L, "sensor1");
        MeasurementDTO dto = new MeasurementDTO(30.2, false, new SensorDTO("sensor1"));
        Measurement measurement = new Measurement(1L, dto.getValue(), dto.getRaining(), sensor);

        when(sensorRepository.findByName("sensor1")).thenReturn(Optional.of(sensor));
        when(measurementRepository.save(any(Measurement.class))).thenReturn(measurement);

        Measurement result = measurementService.addMeasurement(dto);

        assertNotNull(result);
        assertEquals(30.2, result.getValue());
    }

    @Test
    void testGetAllMeasurements() {
        List<Measurement> measurements = List.of(
                new Measurement(1L, 22.0, true, null),
                new Measurement(2L, 18.5, false, null)
        );

        when(measurementRepository.findAll()).thenReturn(measurements);

        List<Measurement> result = measurementService.getAllMeasurements();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetRainyDaysCount() {
        when(measurementRepository.countByRainingTrue()).thenReturn(10L);

        long count = measurementService.getRainyDaysCount();

        assertEquals(10, count);
    }

    @Test
    void testAddRandomMeasurements() {
        Sensor sensor = new Sensor(1L, "sensor1");

        when(sensorRepository.findByName("sensor1")).thenReturn(Optional.of(sensor));

        measurementService.addRandomMeasurements("sensor1", 5);

        verify(measurementRepository, times(5)).save(any(Measurement.class));
    }
}