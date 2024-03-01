
package com.example.Eventic_backend.Controller.Admin;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.User;
import com.example.Eventic_backend.Repository.UserRepository;
import com.example.Eventic_backend.Repository.Admin.EventRepository;
import com.example.Eventic_backend.Service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController

public class EventController {

    @Autowired
    private EventRepository eventRepository;

  @GetMapping("/api/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }
  

    @PostMapping("/api/add-event")
    public ResponseEntity addEvent(@RequestBody Event event) {
       try {


         // System.out.println(event.getEmail());
          
            Event save = eventRepository.save(event);
            //System.out.println(user.getPassword());
           // String token = save.getUserName();//need to encode it
            //System.out.println(token);// Generate JWT token
            return ResponseEntity.ok().body("User registered successfully");
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    // @PostMapping("/api/login")
    // public ResponseEntity login(@RequestBody User user) {
   
    //         try {
    //             // Authentication authentication = authenticationManager.authenticate(
    //             //         new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    
    //             // SecurityContextHolder.getContext().setAuthentication(authentication);
    
    //             User save = userRepository.findByUserName(user.getUserName())
    //                 .orElseThrow(() -> new RuntimeException("User not found"));
    

    //                 boolean passwordMatch = BCrypt.checkpw(user.getPassword(), save.getPassword());
    //                 if (!passwordMatch) {
    //                     throw new RuntimeException("Invalid username or password");
    //                 }

    //             String token = save.getUserName();
    
    //             return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("Login successful !");
    //         } catch (Exception ex) {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password !");
    //         }

    // }
}
