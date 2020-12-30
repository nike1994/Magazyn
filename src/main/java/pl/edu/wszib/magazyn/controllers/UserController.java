package pl.edu.wszib.magazyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.magazyn.model.User;
import pl.edu.wszib.magazyn.services.IUserService;
import pl.edu.wszib.magazyn.session.SessionObj;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @Resource
    SessionObj sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        this.userService.authenticate(user);
        return "redirect:/main";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.userService.logout();
        return "redirect:/main";
    }
}
