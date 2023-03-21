package com.training.controller;


import com.training.model.Cart;
import com.training.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("/order")
@SessionAttributes("cart")
public class OrderController {

    UserServiceImpl service;

    @Autowired
    OrderController (UserServiceImpl service){
        this.service = service;
    }
    @PostMapping
    public String order(@ModelAttribute("cart") Cart cart, Authentication authentication, Model model) throws SQLException {
        model.addAttribute("welcomeMessage", "Dear " + authentication.getName() + ", your order:");
        model.addAttribute("totalCost", "Total price: " + cart.getTotalCost());
        model.addAttribute("cart", cart);
        service.getUserByUsername(authentication.getName());
        return "order/order";
    }

}
