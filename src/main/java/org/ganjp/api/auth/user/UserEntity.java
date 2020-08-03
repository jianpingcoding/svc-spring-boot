package org.ganjp.api.auth.user;

import lombok.*;
import org.ganjp.api.core.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * UserEntity store user name and password.
 * The UserEntity will map to user table in dao.
 *
 * @author Jianping
 * @date 30/05/2020
 * @version 1.0.0
 *
 */
@Data
@Entity
@Table(name = "auth_user")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString(callSuper = true)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "name", length = 32, unique = true)
    private String username;

    @Column(name = "mobile_number", length = 32, unique = true)
    private String mobileNumber;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastLogin;
}
