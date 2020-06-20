package org.ganjp.api.auth.service;

import org.ganjp.api.auth.dao.UserRepository;
import org.ganjp.api.auth.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
@Transactional
public class UserService {

    /**
     * <p>Find a UserEntity by user name</p>
     *
     * @param userName
     * @return UserEntity
     */
    @Transactional(readOnly = true)
    public UserEntity findByName(String userName) {
        return userRepository.findByName(userName);
    }

    /**
     * <p>Find all the UserEntities</p>
     *
     * @return List<UserEntity>
     */
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


    @Autowired
    private UserRepository userRepository;
}
