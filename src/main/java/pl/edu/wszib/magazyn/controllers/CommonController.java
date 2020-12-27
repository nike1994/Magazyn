package pl.edu.wszib.magazyn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
    // TODO: 27.12.2020  stworzenie kontrolera dla użytkownika (logowanie)
    // TODO: 27.12.2020  stworzenie kontrolera dla produktów (dodawanie, zmiana, usuwanie, wyświetlenie wszystkich)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/main";
    }
}

