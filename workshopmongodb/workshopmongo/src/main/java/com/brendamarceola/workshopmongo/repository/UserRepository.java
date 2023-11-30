package com.brendamarceola.workshopmongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.brendamarceola.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	User save(Optional<User> obj);

	Optional<User> findById(User user);


}
