package pl.edu.wszib.magazyn.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.magazyn.model.ProductInstance;
import pl.edu.wszib.magazyn.services.IProductService;
import pl.edu.wszib.magazyn.session.SessionObj;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.LinkedHashMap;

@Controller
@Validated
public class ProductsController {
    @Autowired
    IProductService productService;

    @Resource
    SessionObj sessionObject;

    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    // TODO: 27.12.2020 (dodawanie, zmiana, usuwanie, wyświetlenie wszystkich)
    // TODO: 28.12.2020 walidacja danych oraz sprawdzanie czy używa ich użytkownik zalogowany

    @RequestMapping(value = "/getProductByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    @ResponseBody
    public String getProductByIDJSON(@RequestBody LinkedHashMap JSON){

        System.out.println("id = " + JSON.get("id"));

        ProductInstance response = this.productService.getProductById((Integer) JSON.get("id"));

        try {
            return this.objectWriter.writeValueAsString(response);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }


    @RequestMapping(value = "/getProductByID/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getProductByID(@PathVariable int id){

        ProductInstance response = this.productService.getProductById(id);

        try {
            return this.objectWriter.writeValueAsString(response);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }



}
