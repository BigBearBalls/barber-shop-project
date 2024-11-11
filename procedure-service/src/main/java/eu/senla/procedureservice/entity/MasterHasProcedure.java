package eu.senla.procedureservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master_has_procedures", schema = "procedure_service_schema")
public class MasterHasProcedure {
    @Id
    @Column(name = "master_execution_id")
    private Integer id;
    @Column(name = "master_id")
    private UUID masterId;
    @Column(name = "procedure_id")
    private Integer procedureId;
}