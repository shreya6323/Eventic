package com.example.Eventic_backend.Model;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.annotation.Generated;
import jakarta.persistence.Id;

@NoArgsConstructor
@ToString

@Document("event")
@Getter
@Setter
public class Event {
    
    @Id
    @GeneratedValue
    String eventid;

    String eventname;

    String location;

    String category;

    @Temporal(TemporalType.DATE)
    private Date eventDate;

    @Temporal(TemporalType.TIME)
    private LocalTime eventTime;

    private BigDecimal price; //change to indainn ruppes
 
    private String description;

    byte[]  poster;

    private int seats;

    private int sold_tickets;

    








    
}
