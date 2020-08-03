package org.ganjp.api.auth.user;

import org.ganjp.api.core.web.response.success.SuccessResponse;
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

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserDTO user) {
        userService.save(user);
    }

    @Autowired
    UserService userService;

}
