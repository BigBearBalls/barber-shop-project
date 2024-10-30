package eu.senla.catalog.repository;

import eu.senla.catalog.entity.Master;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends CrudRepository<Master, UUID> {
}
