package eu.senla.catalog.repository;

import eu.senla.catalog.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {

    Procedure getByName(String name);
}
