package eu.senla.procedureservice.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "procedures_id_gen")
    @SequenceGenerator(name = "procedures_id_gen", sequenceName = "procedures_booking_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "procedure_name")
    private String procedureName;

    @Column(name = "procedure_price")
    private BigDecimal procedurePrice;

    @Column(name = "procedure_duration")
    private Integer procedureDuration;

}