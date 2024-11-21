package eu.senla.procedureservice.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "procedures", schema = "procedure_service_schema")
@Builder
@ToString
public class Procedure {

    @Id
    @Column(name = "procedure_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "procedure_name")
    private String procedureName;

    @Column(name = "procedure_price")
    private BigDecimal procedurePrice;

    @Column(name = "procedure_duration")
    private Integer procedureDuration;

}