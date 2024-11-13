package eu.senla.procedureservice.data.repository;

import eu.senla.procedureservice.data.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
    boolean existsByProcedureName(String procedureName);
}
