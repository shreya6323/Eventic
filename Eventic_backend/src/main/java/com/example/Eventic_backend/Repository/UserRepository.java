package com.example.Eventic_backend.Repository;
import com.example.Eventic_backend.Model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
    Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email);
}
