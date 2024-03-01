package com.example.Eventic_backend.Repository.Admin;
import com.example.Eventic_backend.Model.Admin;
import com.example.Eventic_backend.Model.Event;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event,String>{

 
}
