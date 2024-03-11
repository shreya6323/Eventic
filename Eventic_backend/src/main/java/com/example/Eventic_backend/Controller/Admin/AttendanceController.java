package com.example.Eventic_backend.Controller.Admin;

import com.example.Eventic_backend.Model.Attendance;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Ticket;
import com.example.Eventic_backend.Repository.Admin.AttendanceRepository;
import com.example.Eventic_backend.Repository.Admin.EventRepository;
import com.example.Eventic_backend.Repository.Admin.TicketRepository;
import com.example.Eventic_backend.Service.Admin.AttandanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

 @Autowired
  private AttandanceService attandanceService;

  
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

 @Autowired
    private TicketRepository ticketRepository;

//     @GetMapping("/{eventname}")
//     public ResponseEntity<List<Attendance>> getAllAttendance(@PathVariable String eventname) {
//     Event event =  eventRepository.findByEventname(eventname);
//     System.out.println(event);
//     List<Ticket> tickets=ticketRepository.findByEvent(event);
//     System.out.println(tickets);

//    List<Attendance> attendances=null;
//      // List <Ticket>  tickets = ticketRepository.findByEvent(event);
//       for (Ticket ticket : tickets) {
//         // Retrieve attendance records for each ticket
//       Attendance attendance = attendanceRepository.findByTicket(ticket);
//       System.out.println(attendance);
//         attendances.add(attendance);
//       }
//       System.out.println(attendances);


// //     }

//      return ResponseEntity.ok(attendances);
   
//     }


@GetMapping("/{eventname}")
public ResponseEntity<List<Attendance>> getAllAttendance(@PathVariable String eventname) {
    // Event event = eventRepository.findByEventname(eventname);
    // if (event == null) {
    //     return ResponseEntity.notFound().build(); // Return 404 if event not found
    // }
    // System.out.println(event.getEventname());

    // List<Ticket> tickets = new ArrayList<>();
    // //tickets=ticketRepository.findByEvent(event);
    // System.out.println(tickets.size());
    List<Attendance>finallist = new ArrayList<>();
    List<Attendance> attendances = new ArrayList<>(); // Initialize the list

    // for (Ticket ticket : tickets) {
    //     Attendance attendance = attendanceRepository.findByTicket(ticket);
    //     if (attendance != null) {
    //         attendances.add(attendance);
    //     }
    // }

    attendances = attendanceRepository.findAll();
    for(Attendance attendance : attendances){
       Ticket ticket = attendance.getTicket();
       Event event =  ticket.getEvent();

        if(event.getEventname().equals(eventname)){
                 finallist.add(attendance);
        }

    }

    return ResponseEntity.ok(finallist);
}

}


