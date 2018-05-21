package rma.project.rma.Repos;


import rma.project.rma.Entity.User;

/**
 * Created by gerli on 06/03/2018.
 */
public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);
}
