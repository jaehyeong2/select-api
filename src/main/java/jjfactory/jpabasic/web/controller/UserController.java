package jjfactory.jpabasic.web.controller;

import jjfactory.jpabasic.domain.user.User;
import jjfactory.jpabasic.service.UserService;
import jjfactory.jpabasic.web.dto.ApiRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ApiRes<?> getUserList(){
        return new ApiRes<>(userService.getUserList());
    }

    @PostMapping("/user")
    public void userSave(User user){
        userService.save(user);
    }
}
