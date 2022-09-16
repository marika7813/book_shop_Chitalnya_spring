package com.kizmari.book_shop_Chitalnya_spring.controllers;

import com.kizmari.book_shop_Chitalnya_spring.models.Product;
import com.kizmari.book_shop_Chitalnya_spring.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CardController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping ("/card")
    public String cardMain (Model model) {
        Iterable <Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "card-main";
    }
    @GetMapping ("/card/add")
    public String cardAdd (Model model) {
        return "card-add";
    }
    @PostMapping ("/card/add")
    public String cardProductAdd (@RequestParam String title, @RequestParam String full_text, @RequestParam Integer price, Model model) {
        Product product = new Product(title, full_text, price);
        productRepository.save(product);
        return "redirect:/card";
    }
    @GetMapping ("/card/{id}")
    public String cardDetails (@PathVariable(value = "id") long id, Model model) {
        if (!productRepository.existsById(id)){
            return "redirect:/card";
        }
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res ::add);
        model.addAttribute("product", res);
        return "card-details";
    }

    @GetMapping ("/card/{id}/edit")
    public String cardEdit (@PathVariable(value = "id") long id, Model model) {
        if (!productRepository.existsById(id)){
            return "redirect:/card";
        }
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res ::add);
        model.addAttribute("product", res);
        return "card-edit";
    }       //    Авторские права принадлежат Киселевой Марии Александровне  GitHub marika7813
    @PostMapping("/card/{id}/edit")
    public String cardProductUpdate (@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String full_text, @RequestParam Integer price, Model model) {

        Product product = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No such data found"));;

        product.setTitle(title);
        product.setFull_text(full_text);
        product.setPrice(price);
        productRepository.save(product);
        return "redirect:/card";
    }

    @PostMapping ("/card/{id}/remove")
    public String cardProductDelete (@PathVariable(value = "id") long id, Model model) {

        Product product = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("No such data found"));;

        productRepository.delete(product);
        return "redirect:/card";
    }

}
