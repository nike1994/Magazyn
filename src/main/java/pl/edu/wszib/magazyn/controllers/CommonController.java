package pl.edu.wszib.magazyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.magazyn.model.User;
import pl.edu.wszib.magazyn.services.IProductService;
import pl.edu.wszib.magazyn.session.SessionObj;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObj sessionObject;

// TODO: 28.12.2020 stworzenie barcode za pomocą barbecue

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("products", this.productService.getAllProducts());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("userModel", new User());
        return "main";
    }
}

