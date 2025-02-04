package kz.nik.sensorspostgswagkeycloack.ControllerTests;

import kz.nik.sensorspostgswagkeycloack.controller.SensorController;
import kz.nik.sensorspostgswagkeycloack.dto.SensorDTO;
import kz.nik.sensorspostgswagkeycloack.model.Sensor;
import kz.nik.sensorspostgswagkeycloack.service.SensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class SensorControllerTest {

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private SensorController sensorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSensor() {
        SensorDTO dto = new SensorDTO("sensor1");
        Sensor sensor = new Sensor(1L, "sensor1");

        when(sensorService.registerSensor(dto)).thenReturn(sensor);

        ResponseEntity<Sensor> response = sensorController.registerSensor(dto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("sensor1", response.getBody().getName());
    }
}