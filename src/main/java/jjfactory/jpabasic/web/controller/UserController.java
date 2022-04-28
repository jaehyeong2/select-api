package jjfactory.jpabasic.web.controller;

import jjfactory.jpabasic.domain.user.Address;
import jjfactory.jpabasic.domain.user.User;
import jjfactory.jpabasic.dto.UserForm;
import jjfactory.jpabasic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/users")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList";
    }

    @GetMapping("/users/new")
    public String createForm(Model model){
        model.addAttribute("userForm",new UserForm());
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String create(@Valid UserForm dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "users/createUserForm";
        }

        Address address = new Address(dto.getCity(),dto.getStreet(),dto.getZipcode());
        User user = new User(dto.getName(),address);

        userService.save(user);
        return "redirect:/";
    }

}
