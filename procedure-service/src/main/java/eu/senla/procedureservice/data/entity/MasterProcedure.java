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
@Table(name = "masters_procedures", schema = "procedure_service_schema")
public class MasterProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "master_execution_id")
    private UUID id;

    @Column(name = "master_id")
    private UUID masterId;

    @Column(name = "procedure_id")
    private UUID procedureId;
}