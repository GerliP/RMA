package rma.project.rma.Repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rma.project.rma.Entity.Device;

/**
 * Created by gerli on 06/04/2018.
 */
@Repository
public interface DeviceRepository extends CrudRepository<Device,Integer> {

    Device findById(int id);
    Device findByProductId(String productId);

}
