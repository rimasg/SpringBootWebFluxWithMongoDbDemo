package com.sid.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sid.demo.data.entities.User;
import com.sid.demo.data.repositories.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Flux<User> getAllUsers() {
        return this.repository.findAll();
    }

    public Mono<User> getUserById(final String id) {
        return this.repository.findById(id);
    }

    public Flux<User> getUserByName(final String name) {
        return this.repository.findByNameIgnoreCaseStartingWith(name);
    }

    public Mono<User> saveUser(final User user) {
        return this.repository.save(user);
    }

    public Mono<User> updateUser(final User user) {
        return this.repository.findById(user.getId())
                .map(el -> user)
                .flatMap(this.repository::save);
    }

    public Mono<Void> deleteUser(final String id) {
        return this.repository.deleteById(id);
    }

}
