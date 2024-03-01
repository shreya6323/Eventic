
package com.example.Eventic_backend.Controller.Admin;
import com.example.Eventic_backend.Model.User;
import com.example.Eventic_backend.Repository.UserRepository;
import com.example.Eventic_backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController

public class AdminController {

    @Autowired
    private UserRepository userRepository;

  

    @PostMapping("/api/register")
    public ResponseEntity register(@RequestBody User user) {
       try {


          System.out.println(user.getEmail());
          
          String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
      
            user.setPassword(hashedPassword);
            User save = userRepository.save(user);
            System.out.println(user.getPassword());
            String token = save.getUserName();//need to encode it
            System.out.println(token);// Generate JWT token
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("User registered successfully");
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody User user) {
   
            try {
                // Authentication authentication = authenticationManager.authenticate(
                //         new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    
                // SecurityContextHolder.getContext().setAuthentication(authentication);
    
                User save = userRepository.findByUserName(user.getUserName())
                    .orElseThrow(() -> new RuntimeException("User not found"));
    

                    boolean passwordMatch = BCrypt.checkpw(user.getPassword(), save.getPassword());
                    if (!passwordMatch) {
                        throw new RuntimeException("Invalid username or password");
                    }

                String token = save.getUserName();
    
                return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("Login successful !");
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password !");
            }

    }
}
