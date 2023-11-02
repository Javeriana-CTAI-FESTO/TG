package co.edu.javeriana.ctai.tgsecurity.repository.interfaces.users;

import co.edu.javeriana.ctai.tgsecurity.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}
