package com.example.Eventic_backend.Repository.Admin;
import com.example.Eventic_backend.Model.Admin;
import com.example.Eventic_backend.Model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String>{

 Optional<Admin> findByUserName(String username);
    Optional<Admin> findByEmail(String email);
 
}
