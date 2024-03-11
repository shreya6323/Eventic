package com.example.Eventic_backend.Repository.Admin;

import com.example.Eventic_backend.Model.Admin;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Photo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface PhotoRepository extends MongoRepository<Photo,String> {
     List<Photo> findByEvent(Event event);
}
