package org.ganjp.api.auth.dao;

import org.ganjp.api.auth.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * UserRepository access author_user table in the dao.
 *
 * @author Jianping
 * @date 20/06/2020
 * @version 1.0.0
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    /**
     * <p>Find a UserEntity by user name with ignore case</p>
     *
     * @param name userName
     * @return UserEntity
     */
    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.name) = LOWER(:name)")
    UserEntity findByName(@Param("name") String name);

}
