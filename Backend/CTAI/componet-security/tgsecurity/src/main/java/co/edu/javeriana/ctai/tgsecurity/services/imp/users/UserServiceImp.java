package co.edu.javeriana.ctai.tgsecurity.services.imp.users;

import co.edu.javeriana.ctai.tgsecurity.entities.users.User;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.users.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.services.interfaces.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}
