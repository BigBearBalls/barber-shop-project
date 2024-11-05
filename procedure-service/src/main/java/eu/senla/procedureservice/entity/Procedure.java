package eu.senla.procedureservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "procedures", schema = "my_schema")
public class Procedure {
    @Id
    @Column(name = "procedure_id")
    private Integer id;

    @Column(name = "procedure_name")
    private String procedureName;

    @Column(name = "procedure_price")
    private BigDecimal procedurePrice;

    @Column(name = "procedure_duration")
    private Integer procedureDuration;

}