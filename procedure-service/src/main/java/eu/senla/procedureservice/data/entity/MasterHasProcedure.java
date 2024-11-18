package eu.senla.procedureservice.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "masters_has_procedures", schema = "procedure_service_schema")
public class MasterHasProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_execution_id")
    private Integer id;

    @Column(name = "master_id")
    private UUID masterId;

    @Column(name = "procedure_id")
    private Integer procedureId;
}