package co.edu.javeriana.ctai.tgsecurity.service.imp;

import co.edu.javeriana.ctai.tgsecurity.service.intf.IUserService;
import co.edu.javeriana.ctai.tgsecurity.patterns.model.User;
import co.edu.javeriana.ctai.tgsecurity.repository.IUserRepository;
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
