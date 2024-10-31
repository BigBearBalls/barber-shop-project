package eu.senla.catalog.repository;

import eu.senla.catalog.entity.Master;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, UUID> {
}
