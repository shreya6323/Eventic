package com.example.Eventic_backend.Service;


import com.example.Eventic_backend.Model.User;
import com.example.Eventic_backend.Repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserService  {

    private final UserRepository userRepository;


     
}