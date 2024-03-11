package com.example.Eventic_backend.Model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    String id;

   @OneToOne
   Ticket ticket;


   
   LocalDateTime time;

   



}
