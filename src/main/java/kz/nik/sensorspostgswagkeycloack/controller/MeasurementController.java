package kz.nik.sensorspostgswagkeycloack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.nik.sensorspostgswagkeycloack.dto.MeasurementDTO;
import kz.nik.sensorspostgswagkeycloack.model.Measurement;
import kz.nik.sensorspostgswagkeycloack.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementService measurementService;

    @Operation(summary = "Add new measurement", description = "Adds a new measurement for a sensor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Measurement added successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/add")
    public ResponseEntity<Measurement> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO) {
        return ResponseEntity.ok(measurementService.addMeasurement(measurementDTO));
    }

    @Operation(summary = "Get all measurements", description = "Retrieves all recorded measurements.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of measurements retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<List<Measurement>> getAllMeasurements() {
        return ResponseEntity.ok(measurementService.getAllMeasurements());
    }

    @Operation(summary = "Get rainy days count", description = "Returns the number of rainy days recorded.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rainy days count retrieved successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Long> getRainyDaysCount() {
        return ResponseEntity.ok(measurementService.getRainyDaysCount());
    }

    @Operation(summary = "Add random measurements",
            description = "Adds a specified number of random measurements for a given sensor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random measurements added successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/addRandom")
    public ResponseEntity<String> addRandomMeasurements(@RequestParam String sensorName, @RequestParam int count) {
        measurementService.addRandomMeasurements(sensorName, count);
        return ResponseEntity.ok(count + " random measurements added!");
    }
}