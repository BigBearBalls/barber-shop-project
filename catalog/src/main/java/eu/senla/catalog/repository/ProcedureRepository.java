package eu.senla.catalog.repository;

import eu.senla.catalog.entity.Procedure;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

    boolean existsByName(String name);

    Procedure findByName(String name);

    List<Procedure> findAll();
}
