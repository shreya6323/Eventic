package com.example.Eventic_backend.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Feedback;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback,String>{



    List<Feedback> findByEvent(Event event);
    
}
