
package com.example.Eventic_backend.Controller.Admin;
import com.example.Eventic_backend.Model.Admin;
import com.example.Eventic_backend.Model.Attendance;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Ticket;
import com.example.Eventic_backend.Model.User;
import com.example.Eventic_backend.Repository.UserRepository;
import com.example.Eventic_backend.Repository.Admin.AdminRepository;
import com.example.Eventic_backend.Repository.Admin.AttendanceRepository;
import com.example.Eventic_backend.Repository.Admin.EventRepository;
import com.example.Eventic_backend.Service.UserService;
import com.example.Eventic_backend.Service.Admin.EventService;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
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


    
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private EventService eventService;

  @GetMapping("/api/events/{adminName}")

  public ResponseEntity<Optional<List<Event>>> getEventsByOrganizerId(@PathVariable String adminName) {
     Optional<Admin> admin = adminRepository.findByUserName(adminName);
     System.out.println(adminName);
    Optional<List<Event>> events = eventRepository.findByAdmin(admin);
      if (events.isEmpty()) {
          return ResponseEntity.notFound().build();
      } else {
          return ResponseEntity.ok(events);
        }
    }


    @GetMapping("/api/event/{eventname}")

    public ResponseEntity<Event> getEventsByEventName(@PathVariable String eventname) {
       Event eve = eventRepository.findByEventname(eventname);
   
     System.out.println("showing......");
        if (eve == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(eve);
          }
      }


      @GetMapping("/api/eventhist/{userName}")

    public ResponseEntity<List<Event>> getEventHist(@PathVariable String userName) {
         List<Event> hist = new ArrayList<>();
        List<Attendance> attendances = attendanceRepository.findAll();
            for(Attendance attendance : attendances){
       Ticket ticket = attendance.getTicket();
      User user = ticket.getUser();
      String name = user.getUserName();

        if(name.equals(userName)){
            hist.add(ticket.getEvent());
        }

    }

    return ResponseEntity.ok(hist);




      }

    @GetMapping("/api/events")

    public ResponseEntity<List<Event>> getAllEvents() {
    
   
      List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(events);
          }
      }

    @GetMapping("/api/events/update/{eventname}")
    public ResponseEntity<Event> getEventbyEventName(@PathVariable String eventname) {
       Event event = eventRepository.findByEventname(eventname);
       System.out.println(eventname);
    
        if (event == null) {
            return ResponseEntity.notFound().build();
        } else {
            eventRepository.save(event);
            return ResponseEntity.ok(event);
          }
      }


    @DeleteMapping("/api/events/delete/{eventname}")
    public ResponseEntity<?> deleteEvent(@PathVariable String eventname) {
        try {
            System.out.println("I am in event deletion !");
            // Check if the event exists
            Event event = eventRepository.findByEventname(eventname);
            if (event == null) {
                return ResponseEntity.notFound().build();
            }

            // If the event exists, delete it
            eventRepository.deleteByEventname(eventname);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting event: " + e.getMessage());
        }
    }

  

    // @PostMapping("/api/add-event")
    // public ResponseEntity addEvent(@RequestBody Event event) {
    //    try {


    //      // System.out.println(event.getEmail());
    //     //  byte[] posterData = Base64.getDecoder().decode(event.getPoster());
    //     //  // Set the decoded poster data to the poster field of the Event entity
    //     //  event.setPoster(posterData);
    //     // byte[] posterData = Base64.getDecoder().decode(event.getPoster());
    //     // Set the decoded poster data to the poster field of the Event entity\ 
    //        System.out.println(event.getPoster());
    //     // event.setPoster(posterData);

    //         Event save = eventRepository.save(event);
    //         //System.out.println(user.getPassword());
    //        // String token = save.getUserName();//need to encode it
    //         //System.out.println(token);// Generate JWT token
    //         return ResponseEntity.ok().body("Event registered successfully");
    //     } catch (Exception e){
    //         System.out.println(e);
    //         return ResponseEntity.internalServerError().body(e.getMessage());
    //     }
    // }


@PostMapping("/api/admin/add-event")
    public ResponseEntity<?> createEvent(@RequestBody Event event,@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        try {
            
            String token = authorizationHeader.substring("Bearer ".length()).trim();
            eventService.createEvent(event, token);
            return ResponseEntity.ok("Event created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @PutMapping("/api/admin/update-event/{eventname}")
    public ResponseEntity<?> updateEvent(@PathVariable String eventname, @RequestBody Event eventDetails) {
        // Retrieve the event from the repository
    Event existingEvent = eventRepository.findByEventname(eventname);
    
        // Check if the event exists
        if (existingEvent != null) {
            // If it exists, update its fields
          
            existingEvent.setLocation(eventDetails.getLocation());
            existingEvent.setCategory(eventDetails.getCategory());
            existingEvent.setEventDate(eventDetails.getEventDate());
            existingEvent.setEventTime(eventDetails.getEventTime());
            existingEvent.setDescription(eventDetails.getDescription());
            existingEvent.setPoster(eventDetails.getPoster());
            existingEvent.setSeats(eventDetails.getSeats());
    
            // Save the updated event
            eventRepository.save(existingEvent);
    
            return ResponseEntity.ok().build();
        } else {
            // If the event doesn't exist, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
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

