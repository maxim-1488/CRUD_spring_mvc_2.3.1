package ru.maximkrylov.spring_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.maximkrylov.spring_mvc.model.User;
import ru.maximkrylov.spring_mvc.service.UserService;

@Controller
public class CarController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String listUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("allUsers", userService.getAllUsers());
        return "users";
    }

    @PostMapping(value = "/users/add")
    public String addUser(@ModelAttribute("users") User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @GetMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

    //страница для редактирования юзеров
    @GetMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.getUserById(id));
        modelAndView.addObject("allUsers", userService.getAllUsers());
        return "users";
    }

    //посмотреть каждого юзера на отдельной странице
    @GetMapping(value = "/userdata/{id}")
    public String userData(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.getUserById(id));
        return "userdata";
    }
}
