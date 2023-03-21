package com.training.controller;

import com.training.model.Cart;
import com.training.model.Good;
import com.training.service.impl.GoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("/addToCart")
@SessionAttributes("cart")
public class AddToCartController {

    GoodServiceImpl service;

    @Autowired
    AddToCartController(GoodServiceImpl service){
        this.service = service;
    }

    @PostMapping
    public String addToCart(Model model,
                            @ModelAttribute("cart") Cart cart,
                            @ModelAttribute("item") Good good) throws SQLException {
            if(cart == null){
                Cart shoppingCart = new Cart();
                shoppingCart.addGood(service.getById(good.getId()));
                model.addAttribute("cart", shoppingCart);
            } else {
                cart.addGood(service.getById(good.getId()));
            }
        return "redirect:/shop";
    }

    @ModelAttribute
    public Cart cart(){
        return new Cart();
    }

}
