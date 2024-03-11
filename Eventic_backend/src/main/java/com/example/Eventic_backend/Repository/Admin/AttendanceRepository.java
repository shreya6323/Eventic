package com.example.Eventic_backend.Repository.Admin;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.Eventic_backend.Model.Attendance;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Ticket;


@Repository
public interface AttendanceRepository extends MongoRepository<Attendance,String>  {

    Attendance findByTicket(Ticket ticket);

  

   
    
} 
    

