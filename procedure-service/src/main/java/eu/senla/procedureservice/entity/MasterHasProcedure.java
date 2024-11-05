package eu.senla.procedureservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "master_has_procedures", schema = "my_schema")
public class MasterHasProcedure {
    @Id
    @Column(name = "master_execution_id", nullable = false)
    private Integer id;

}