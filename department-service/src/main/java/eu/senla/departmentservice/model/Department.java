package eu.senla.departmentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department", schema = "department_service_schema")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "team_leader_id")
    private UUID teamLeaderId;

    @OneToMany(mappedBy = "department")
    private Set<User> users;
}
