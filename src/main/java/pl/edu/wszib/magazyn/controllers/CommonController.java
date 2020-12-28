package pl.edu.wszib.magazyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.magazyn.services.IProductService;
import pl.edu.wszib.magazyn.session.SessionObj;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObj sessionObject;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String main(Model model){
        model.addAttribute("products", this.productService.getAllProducts());
        model.addAttribute("isLogged", this.sessionObject);
        return "main";
    }
}

