package com.example.Eventic_backend.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Document("feedback")
@Getter
@Setter
public class Feedback {

       
    @Id
    // @GeneratedValue
    String id;

    String description;
    
    String rating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
   Event event;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "event_id")
   User user;


    
}
