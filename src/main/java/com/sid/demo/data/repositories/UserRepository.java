package com.sid.demo.data.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sid.demo.data.entities.User;

import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findByNameIgnoreCaseStartingWith(String name);
}
