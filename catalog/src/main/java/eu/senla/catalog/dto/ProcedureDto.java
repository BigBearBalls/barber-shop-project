package eu.senla.catalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public record ProcedureDto(@Size(min = 5, max = 200, message = "procedure-name-size-message")
                           @NotNull(message = "null-field-message")
                           String name,
                           @Min(value = 0, message = "not-positive-price-message")
                           @NotNull(message = "null-field-message")
                           Double price) {
}
