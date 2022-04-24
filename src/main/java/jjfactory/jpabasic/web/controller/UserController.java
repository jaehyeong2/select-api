package jjfactory.jpabasic.web.controller;

import jjfactory.jpabasic.domain.user.User;
import jjfactory.jpabasic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void userSave(User user){
        userService.save(user);
    }
}
