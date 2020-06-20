package org.ganjp.api.auth.controller;

import org.ganjp.api.auth.dao.entity.UserEntity;
import org.ganjp.api.auth.service.UserService;
import org.ganjp.api.core.web.model.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    /**
     * <p>Find all entities</p>
     *
     */
    @GetMapping(value = "")
    public SuccessResponse findAll() {
        List<UserEntity> userEntities = userService.findAll();
        return new SuccessResponse("Find users success", userEntities);
    }


    @Autowired
    UserService userService;

}
