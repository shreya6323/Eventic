package com.example.Eventic_backend.Controller;
import com.example.Eventic_backend.Model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

// @RestController
// @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.POST})
@RestController
public class EmailController {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/api/send-email")
    public String sendEmail(@RequestParam("recipientEmail") String recipientEmail,
                            @RequestParam("ticketImage") MultipartFile ticketImage) {
        try {
            // Create MimeMessage
            MimeMessage message = emailSender.createMimeMessage();

            // Enable the multipart flag to send attachments
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set recipient email address
            helper.setTo(recipientEmail);

            // Set email subject
            helper.setSubject("Grab your Ticket with Eventic !");

            // Set email body
            helper.setText("Dear Customer,\n\nPlease find your event ticket attached.\n\nBest regards,\nEventic Team");

            // Add attachment
            helper.addAttachment(ticketImage.getOriginalFilename(), new ByteArrayResource(ticketImage.getBytes()));

            // Send email
            emailSender.send(message);

            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }
}