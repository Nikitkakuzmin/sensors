package kz.nik.sensorspostgswagkeycloack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {
    @NotBlank(message = "Sensor name cannot be empty")
    private String name;
}