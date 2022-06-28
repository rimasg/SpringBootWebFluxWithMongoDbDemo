package com.sid.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.sid.demo.data.entities.User;
import com.sid.demo.services.UserService;

import reactor.core.publisher.Flux;

@Controller
public class GreetingController {

    @Autowired
    private UserService userService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        final Flux<User> allUsers = userService.getAllUsers();
        final IReactiveDataDriverContextVariable reactiveDataDrivenMode = 
                new ReactiveDataDriverContextVariable(allUsers, 1);
        Long userCount = allUsers.count().block();

        model.addAttribute("userCount", userCount);
        model.addAttribute("users", reactiveDataDrivenMode);
        model.addAttribute("name", name);
        return "greeting";
    }
}
