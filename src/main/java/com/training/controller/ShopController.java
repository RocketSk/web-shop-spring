package com.training.controller;

import com.training.model.Good;
import com.training.service.impl.GoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final GoodServiceImpl service;

    @Autowired
    ShopController(GoodServiceImpl service) {
        this.service = service;
    }

    @GetMapping()
    public String shop(Model model, Authentication authentication) throws SQLException {
        String welcomeMessage = "Welcome, " + authentication.getName();
        model.addAttribute("items", service.getAll());
        model.addAttribute("item", new Good());
        model.addAttribute("welcomeMessage", welcomeMessage);
        return "shop/shop";
    }

}
