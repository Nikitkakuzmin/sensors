package kz.nik.sensorspostgswagkeycloack.ControllerTests;

import kz.nik.sensorspostgswagkeycloack.controller.MeasurementController;
import kz.nik.sensorspostgswagkeycloack.dto.MeasurementDTO;
import kz.nik.sensorspostgswagkeycloack.dto.SensorDTO;
import kz.nik.sensorspostgswagkeycloack.model.Measurement;
import kz.nik.sensorspostgswagkeycloack.service.MeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MeasurementControllerTest {
    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private MeasurementController measurementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMeasurement() {
        MeasurementDTO dto = new MeasurementDTO(25.5, true, new SensorDTO("sensor1"));
        Measurement measurement = new Measurement(1L, dto.getValue(), dto.getRaining(), null);

        when(measurementService.addMeasurement(dto)).thenReturn(measurement);

        ResponseEntity<Measurement> response = measurementController.addMeasurement(dto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(25.5, response.getBody().getValue());
    }

    @Test
    void testGetAllMeasurements() {
        List<Measurement> measurements = List.of(
                new Measurement(1L, 23.4, false, null),
                new Measurement(2L, 19.8, true, null)
        );

        when(measurementService.getAllMeasurements()).thenReturn(measurements);

        ResponseEntity<List<Measurement>> response = measurementController.getAllMeasurements();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetRainyDaysCount() {
        when(measurementService.getRainyDaysCount()).thenReturn(5L);

        ResponseEntity<Long> response = measurementController.getRainyDaysCount();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(5, response.getBody());
    }

    @Test
    void testAddRandomMeasurements() {
        doNothing().when(measurementService).addRandomMeasurements("sensor1", 1000);

        ResponseEntity<String> response = measurementController.addRandomMeasurements("sensor1", 1000);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("1000 random measurements added!", response.getBody());
    }
}
