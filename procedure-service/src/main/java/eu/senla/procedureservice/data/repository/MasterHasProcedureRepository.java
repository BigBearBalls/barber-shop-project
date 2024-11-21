package eu.senla.procedureservice.data.repository;

import eu.senla.procedureservice.data.entity.MasterHasProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MasterHasProcedureRepository extends JpaRepository<MasterHasProcedure, UUID> {

    boolean existsByProcedureIdAndMasterId(UUID procedureId, UUID masterId);
}
