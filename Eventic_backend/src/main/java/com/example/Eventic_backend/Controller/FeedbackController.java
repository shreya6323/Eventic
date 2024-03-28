package com.example.Eventic_backend.Controller;

import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.User;
import com.example.Eventic_backend.Model.Feedback;
import com.example.Eventic_backend.Repository.FeedbackRepository;
import com.example.Eventic_backend.Repository.UserRepository;
import com.example.Eventic_backend.Repository.Admin.EventRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

 @Autowired
 FeedbackRepository feedbackRepository;

 @Autowired
 UserRepository userRepository;

@Autowired
 EventRepository eventRepository;

 @GetMapping("/{eventname}")
    public ResponseEntity<List<Feedback>> getAllFeedbacks(@PathVariable String eventname) {
        Event event = eventRepository.findByEventname(eventname);
        List<Feedback> feedbacks = feedbackRepository.findByEvent(event);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
 
 @PostMapping("/{username}/{eventname}")
 public ResponseEntity<String> submitFeedback(@RequestBody Feedback feedback, @PathVariable String eventname, @PathVariable String username) {
     // Call the service to process the submitted feedback
     Event event = eventRepository.findByEventname(eventname);
     Optional<User> user = userRepository.findByUserName(username);
     if (event != null && user.isPresent()) {
         feedback.setEvent(event);
         feedback.setUser(user.get());
         feedbackRepository.save(feedback);
         return ResponseEntity.ok("Feedback submitted successfully");
     } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event or user not found");
     }
 }
 
}
