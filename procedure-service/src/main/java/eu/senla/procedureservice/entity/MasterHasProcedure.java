package eu.senla.procedureservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master_has_procedures", schema = "my_schema")
public class MasterHasProcedure {
    @Id
    @Column(name = "master_execution_id")
    private Integer id;

}