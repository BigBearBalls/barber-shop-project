package eu.senla.procedureservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "master_execution_id")
    private Integer id;
    @Column(name = "master_id")
    private UUID masterId;
    @Column(name = "procedure_id")
    private Integer procedureId;
}