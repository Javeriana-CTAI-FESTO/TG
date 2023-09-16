package co.edu.javeriana.ctai.tgsecurity.service.intf;

import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User findByUsername(String username);
}
