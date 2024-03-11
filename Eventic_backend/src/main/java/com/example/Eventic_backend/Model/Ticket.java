package com.example.Eventic_backend.Model;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.criteria.Fetch;
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
@Document("ticket")
@Getter
@Setter
public class Ticket {
    
    @Id
    // @GeneratedValue
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
   Event event;


  byte[] qrcode;


    @OneToOne(mappedBy = "ticket")
    private Attendance attendance;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "event_id")
   User user;
    
}







    


