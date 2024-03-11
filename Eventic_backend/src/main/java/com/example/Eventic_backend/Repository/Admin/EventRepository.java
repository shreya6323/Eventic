package com.example.Eventic_backend.Repository.Admin;
import com.example.Eventic_backend.Model.Admin;
import com.example.Eventic_backend.Model.Event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event,String>{

Optional<List<Event>> findByAdmin(Optional<Admin> admin);
List<Event> findAll();
Event findByEventname(String eventname);
Event findByid(String eventid);
void deleteByEventname(String eventname);
   
  


 
}
