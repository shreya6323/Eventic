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
@Document("photo")
@Getter
@Setter
public class Photo {
    
    @Id
    // @GeneratedValue
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
   Event event;

    byte[]  pic;

    
    public void setPic(byte[] pic) {
        this.pic = pic;
    }




    public Event getEvent() {
        return event;
    }


    public byte[] getPic() {
        return pic;
    }





    
}







    


