package rma.project.rma.Repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rma.project.rma.Entity.User;

/**
 * Created by gerli on 20/02/2018.
 */
@Repository
public interface UserRepository extends CrudRepository <User, Integer> {
    User findByEmail(String email);
    User findById(int id);

}
