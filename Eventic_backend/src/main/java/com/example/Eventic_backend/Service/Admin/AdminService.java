package com.example.Eventic_backend.Service.Admin;


import com.example.Eventic_backend.Model.User;
import com.example.Eventic_backend.Repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class AdminService  {

    private final UserRepository userRepository;


     
}