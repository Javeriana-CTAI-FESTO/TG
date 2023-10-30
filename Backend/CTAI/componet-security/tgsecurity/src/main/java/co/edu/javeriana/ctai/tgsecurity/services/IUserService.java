package co.edu.javeriana.ctai.tgsecurity.services;

import co.edu.javeriana.ctai.tgsecurity.entities.users.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User findByUsername(String username);
}
