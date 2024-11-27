package eu.senla.procedureservice.data.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import eu.senla.procedureservice.serializer.NormalizeStringDeserializer;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProcedureRequest {

    @JsonDeserialize(using = NormalizeStringDeserializer.class)
    @NotNull(message = "Procedure name cannot be null")
    private String procedureName;
    @NotNull(message = "Procedure price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Procedure price must be positive")
    private BigDecimal procedurePrice;
    @NotNull(message = "Procedure duration cannot be null")
    @Min(value = 1, message = "Procedure duration cannot be less then 1 minute")
    private Integer procedureDuration;
}
