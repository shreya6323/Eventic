package com.example.Eventic_backend.Repository.Admin;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Ticket;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends MongoRepository<Ticket,String>{

    List<Ticket> findByEvent(Event event);
 
}
