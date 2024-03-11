package com.example.Eventic_backend.Service.Admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.Eventic_backend.Model.Attendance;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Ticket;
import com.example.Eventic_backend.Repository.Admin.AttendanceRepository;
import com.example.Eventic_backend.Repository.Admin.EventRepository;
import com.example.Eventic_backend.Repository.Admin.TicketRepository;
@Service
public class AttandanceService {
     private MongoTemplate mongoTemplate = null;

     @Autowired
     EventRepository eventRepository;

     @Autowired
     TicketRepository ticketRepository;

     @Autowired
     AttendanceRepository attendanceRepository;

    @Autowired
    public AttandanceService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void saveAttendance(Ticket ticket) {
        Attendance attendance=new Attendance();
        // Set current time before saving
        attendance.setTicket(ticket);
        attendance.setTime(LocalDateTime.now());
        mongoTemplate.save(attendance);
    
    }


    // public List<Attendance> getAttendance(String eventname)
    // {
    //  Event event =  eventRepository.findByEventname(eventname);
    // //  List <Ticket>  tickets = ticketRepository.findByEvent(event);
    //  List<Attendance> attendances = attendanceRepository.findByEvent(event);
    //  return attendances;
           
    // }
    
}
