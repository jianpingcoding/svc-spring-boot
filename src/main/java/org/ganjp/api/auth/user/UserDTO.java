package org.ganjp.api.auth.user;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
}
