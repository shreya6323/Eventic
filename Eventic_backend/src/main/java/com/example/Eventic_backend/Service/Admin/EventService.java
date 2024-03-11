package com.example.Eventic_backend.Service.Admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Eventic_backend.Repository.Admin.AdminRepository;
import com.example.Eventic_backend.Model.Admin;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Repository.Admin.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AdminRepository adminRepository;
    
    public void createEvent(Event event, String adminId) {
        // Fetch organizer from the database
        Admin admin = adminRepository.findByUserName(adminId).orElseThrow(() -> new IllegalArgumentException("Organizer not found"));
        
        // Associate event with organizer
        event.setAdmin(admin);
        
        // Save event
        eventRepository.save(event);
    }

    // public Event getEvent(String eventname)
    // {
    //     Optional<Event> opevent = eventRepository.findByEventname(eventname);
    //     return opevent.get();
    // }

    
}
