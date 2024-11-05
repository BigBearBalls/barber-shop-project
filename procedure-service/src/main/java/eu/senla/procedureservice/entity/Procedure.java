package eu.senla.procedureservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "procedures", schema = "my_schema")
public class Procedure {
    @Id
    @Column(name = "procedure_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "procedure_name", nullable = false)
    private String procedureName;

    @Column(name = "procedure_price", precision = 10, scale = 2)
    private BigDecimal procedurePrice;

    @Column(name = "procedure_duration")
    private Integer procedureDuration;

}