package com.example.Eventic_backend.Controller.Admin;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.Eventic_backend.Model.Admin;
import com.example.Eventic_backend.Model.Attendance;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Photo;
import com.example.Eventic_backend.Model.QRCodeGenerator;
import com.example.Eventic_backend.Model.Ticket;
import com.example.Eventic_backend.Model.User;
import com.example.Eventic_backend.Repository.UserRepository;
import com.example.Eventic_backend.Repository.Admin.AdminRepository;
import com.example.Eventic_backend.Repository.Admin.EventRepository;
import com.example.Eventic_backend.Repository.Admin.PhotoRepository;
import com.example.Eventic_backend.Repository.Admin.TicketRepository;
import com.example.Eventic_backend.Service.Admin.AttandanceService;
import com.example.Eventic_backend.Repository.Admin.AttendanceRepository;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
public class TicketController {
    
@Autowired
private EventRepository eventRepository;

@Autowired
private UserRepository userRepository;

@Autowired
private TicketRepository ticketRepository;

@Autowired
private AttendanceRepository attendanceRepository;
@Autowired
private AttandanceService attandanceService;

//   @Value("${razorpay.keyId}")
//     private String keyId;

//     @Value("${razorpay.keySecret}")
//     private String keySecret;

    @GetMapping(value="/api/pay/{amount}",produces="application/json")
    public ResponseEntity<?> initiatePayment(@PathVariable("amount") Double amount) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_yN4QiPyuTxoTfN","Kz8xOtAz6qVHjlm3Fb1me6ss");
            JSONObject options = new JSONObject();
            options.put("amount", amount * 100); // Amount in paise
            options.put("currency", "INR");
            options.put("receipt", "order_receipt");
            System.out.println("hi");
            Order order = razorpayClient.orders.create(options);
            System.out.println("hello");
            JSONObject responseJson = new JSONObject();
            
        String orderId =  order.get("id").toString();

            // Return the order ID in the response body
            return ResponseEntity.ok().body(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to initiate payment");
        }
    }
   

    @PostMapping("/api/tickets/{username}/{eventname}")
    public ResponseEntity<?> addTicket(@PathVariable String username,@PathVariable String eventname) {
        try {
            System.out.println(eventname);
           Ticket ticket = new Ticket();
           Event event = eventRepository.findByEventname(eventname);
       
          // int seats = event.getSeats();
        //   System.out.println(seats);
          // event.setSeats(seats-1);
           int sold = event.getSold_tickets();
           event.setSold_tickets(sold+1);
           System.out.println(event.getEventname());
        //   System.out.println(seats);
           Optional<User> user = userRepository.findByUserName(username);
        // //    System.out.println(event.getEventname());
           eventRepository.save(event);
        // eventRepository.updateSeatsByEventname(eventname, seats - 1);
           User userobj = user.get();
         
        //    System.out.println(userobj.getUserName());
           ticket.setEvent(event);
           ticket.setUser(userobj);
            // System.out.println(image.getBytes());
        //    ticketRepository.save(ticket);
        ticketRepository.save(ticket);
        System.out.println(ticket.getId());
        byte[] qrCodeImage = QRCodeGenerator.generateQR(ticket.getId());
        System.out.println(qrCodeImage);
        ticket.setQrcode(qrCodeImage);

        ticketRepository.save(ticket);
            return ResponseEntity.ok().body(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/api/tickets/{ticketId}/{eventname}")
    public ResponseEntity<?> getTicketById(@PathVariable String ticketId,@PathVariable String eventname) {
        try {
            System.out.println(ticketId);
            // Query the ticket data from the database using the ticketId
            Optional<Ticket> ticket = ticketRepository.findById(ticketId);
            Event event = ticket.get().getEvent();
            String name = event.getEventname();
            if (ticket.isPresent() && name.equals(eventname)) {
                //Attendance attendance=new Attendance();
                // attendance.setTicket(ticket.get());
                // LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
                // System.out.println(currentTime);
                // attendance.setTime(currentTime);
                // attendanceRepository.save(attendance);
                attandanceService.saveAttendance(ticket.get());
                // ticketRepository.deleteById(ticketId);
                
                
                return ResponseEntity.ok().body(ticket.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
     
    // public static byte[] generateQRCodeImage(String ticketId) throws WriterException, IOException {
    //     // Create the ByteMatrix for the QR-Code that encodes the ticket ID
    //     System.out.println("I am in qr code ...");
    //        int width = 300;
    // int height = 300;
    // String format = "png";

    // Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
    // hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

    // QRCodeWriter qrCodeWriter = new QRCodeWriter();
    // BitMatrix bitMatrix = qrCodeWriter.encode(ticketId, BarcodeFormat.QR_CODE, width, height, hintMap);

    // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    // MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
    // return outputStream.toByteArray();

    // }




//       @GetMapping("/api/show_photos/{eventname}")

//   public ResponseEntity<List<Photo>> getEventsByOrganizerId(@PathVariable String eventname) {
//     Event event = eventRepository.findByEventname(eventname);
//     List<Photo> photos = photoRepository.findByEvent(event);
//       if (photos.isEmpty()) {
//           return ResponseEntity.notFound().build();
//       } else {
//           return ResponseEntity.ok(photos);
//         }
//     }


//     @GetMapping("/api/photos")

//     public ResponseEntity<List<Photo>> getAllPhotos() {
 
//       List<Photo> photos = photoRepository.findAll();
//         if (photos.isEmpty()) {
//             return ResponseEntity.notFound().build();
//         } else {
//             return ResponseEntity.ok(photos);
//           }
//       }


//       @DeleteMapping("/api/photos/{photoid}")
//     public ResponseEntity<String> deletePhoto(@PathVariable String photoid) {
//         try {
//             photoRepository.deleteById(photoid);
//             return ResponseEntity.ok("Photo deleted successfully");
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete photo");
//         }
//     }

}
