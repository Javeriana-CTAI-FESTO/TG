package co.edu.javeriana.ctai.tgsecurity.services.imp;

import co.edu.javeriana.ctai.tgsecurity.entities.users.User;
import co.edu.javeriana.ctai.tgsecurity.repository.interfaces.IUserRepository;
import co.edu.javeriana.ctai.tgsecurity.services.IUserService;
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
