package com.stellatech.elopezo.ecommerce.api.users;

import com.stellatech.elopezo.ecommerce.api.users.exceptions.UserAlredyExistsException;
import com.stellatech.elopezo.ecommerce.api.users.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Usuario no encontrado con id: " + id)
        );
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Usuario no encontrado con nombre de usuario: " + username)
        );
    }

    public User registerUser(User user) {
        boolean usernameExists = userRepository.findByUsername(user.getUsername()).isPresent();
        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (usernameExists || emailExists) {
            throw new UserAlredyExistsException("Usuario con el mismo nombre de usuario o correo electrónico ya existe");
        }

        return userRepository.save(user);
    }
}
