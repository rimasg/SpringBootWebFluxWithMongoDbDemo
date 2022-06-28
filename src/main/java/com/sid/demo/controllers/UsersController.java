package com.sid.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sid.demo.data.entities.User;
import com.sid.demo.services.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable String id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/user/name/{name}")
    public Flux<User> getUserByName(@PathVariable String name) {
        return this.userService.getUserByName(name);
    }

    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("/user")
    public Mono<User> createUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PutMapping("/user")
    public Mono<User> updateUser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

}
