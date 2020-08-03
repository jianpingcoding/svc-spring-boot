package org.ganjp.api.auth.user;

import org.ganjp.api.core.exception.UsernameFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * <p>UserService provide the services that create and find a user entity by user name,
 * load user info for authenticate from user table.</p>
 *
 * @author Jianping
 * @date 20/06/2020
 * @version 1.0.0
 *
 */
@Service
public class UserService implements UserDetailsService {

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity save(UserDTO user) {
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        if (userEntity != null) {
            throw new UsernameFoundException();
        }

        userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword().trim()));
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), emptyList());
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
}