package pl.edu.wszib.magazyn.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.magazyn.model.ProductInstance;
import pl.edu.wszib.magazyn.model.User;
import pl.edu.wszib.magazyn.services.IProductService;
import pl.edu.wszib.magazyn.session.SessionObj;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
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

//    @RequestMapping(value = "/getProductByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
//    @ResponseBody
//    public String getProductByIDJSON(@RequestBody LinkedHashMap JSON){
//
//        System.out.println("id = " + JSON.get("id"));
//
//        ProductInstance response = this.productService.getProductById((Integer) JSON.get("id"));
//
//        try {
//            return this.objectWriter.writeValueAsString(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }


//    @RequestMapping(value = "/getProductByID/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public String getProductByID(@PathVariable int id){
//
//        ProductInstance response = this.productService.getProductById(id);
//
//        try {
//            return this.objectWriter.writeValueAsString(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }



    @RequestMapping(value = "/removeProducts", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String removeProductsJSON(@RequestBody LinkedHashMap JSON){

        ArrayList<Integer> ids = (ArrayList<Integer>) JSON.get("id");

        for (int id : ids) {
            this.productService.removeProduct(id);
        }

        return "ok";
    }


    @RequestMapping(value = "/increaseQuantity", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String increaseQuantityJSON(@RequestBody LinkedHashMap JSON){

        ArrayList<Integer> IDs = (ArrayList<Integer>) JSON.get("id");

        for (int id : IDs) {
            this.productService.increaseQuantity(id,(Integer) JSON.get("quantity"));
        }

        return "ok";
    }


    @RequestMapping(value = "/reduceQuantity", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String reduceQuantityJSON(@RequestBody LinkedHashMap JSON){

        ArrayList<Integer> IDs = (ArrayList<Integer>) JSON.get("id");

        for (int id : IDs) {
            this.productService.reduceQuantity(id,(Integer) JSON.get("quantity"));
        }

        return "ok";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProductSite(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        model.addAttribute("productModel", new ProductInstance());
        return "addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProductSite(@ModelAttribute ProductInstance product) {
        this.productService.insertProduct(product);
        return "redirect:/main";
    }


}
