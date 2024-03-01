package com.example.Eventic_backend.Model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@ToString

@Document("admin")
@Getter
@Setter
public class Admin {
   

   public Admin(byte[] p,String name,String email,String phone,String password) 
   {
    this.profilePic = p;
    this.userName   = name;
    this.email = email;
    this.phoneNo = phone;
    this.password = password;
   }
   @Id
   @GeneratedValue
String userId;

    //  @Id
     String userName;


// @Indexed(unique = true)
byte[] profilePic;

    // @Indexed(unique = true)
     String phoneNo;

     String password;

  
    // @Indexed(unique = true)
    String email;

   

 


}
