package kz.nik.sensorspostgswagkeycloack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.nik.sensorspostgswagkeycloack.dto.SensorDTO;
import kz.nik.sensorspostgswagkeycloack.model.Sensor;
import kz.nik.sensorspostgswagkeycloack.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;


    @Operation(summary = "Register a new sensor", description = "Registers a new sensor with the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor registered successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/registration")
    public ResponseEntity<Sensor> registerSensor(@RequestBody @Valid SensorDTO sensorDTO) {
        return ResponseEntity.ok(sensorService.registerSensor(sensorDTO));
    }
}