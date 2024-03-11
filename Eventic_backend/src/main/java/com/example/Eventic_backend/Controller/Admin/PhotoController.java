package com.example.Eventic_backend.Controller.Admin;
// import lombok.AllArgsConstructor;


import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// import org.springframework.web.bind.annotation.RestController;

// @AllArgsConstructor
// @RestController
// public class PhotoController {

    
    
// }
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Eventic_backend.Model.Admin;
import com.example.Eventic_backend.Model.Event;
import com.example.Eventic_backend.Model.Photo;
import com.example.Eventic_backend.Repository.Admin.EventRepository;
import com.example.Eventic_backend.Repository.Admin.PhotoRepository;

@RestController
public class PhotoController {
    
    @Autowired
    private PhotoRepository photoRepository;


    @Autowired
    private EventRepository eventRepository;
    // @PostMapping("/api/photos")
    // public ResponseEntity<?> addPhoto(@RequestParam("image") MultipartFile image) {
    //     try {
    //         System.out.println(image);
    //         // Photo photo = new Photo();
    //         // photo.setPic(pic.getBytes());
    //         // photoRepository.save(photo);
    //         return ResponseEntity.ok().build();
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

    // @PostMapping("/api/photos")
    // public ResponseEntity<?> addPhoto(@RequestParam("image") MultipartFile image) {
    //     try {
    //         // Photo photo = new Photo();
    //         // photo.setPic(Base64.getEncoder().encode(image.getBytes()));
    //         // photoRepository.save(photo);
    //         return ResponseEntity.ok().build();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }


    // @PostMapping("/api/photos")
    // public ResponseEntity<?> addPhoto(@RequestParam("image") MultipartFile image) {
    //     try {
    //         Photo photo = new Photo();
    //         photo.setPic(null);
    //         return ResponseEntity.ok().build();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //    }}
    @PostMapping("/api/photos/{eventname}")
    public ResponseEntity<?> addPhoto(@RequestParam("image") MultipartFile image,@PathVariable String eventname) {
        try {
            System.out.println(eventname);
            Photo photo = new Photo();
            photo.setPic(image.getBytes());
            Event event = eventRepository.findByEventname(eventname);
 photo.setEvent(event);
            // System.out.println(image.getBytes());
            photoRepository.save(photo);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


      @GetMapping("/api/show_photos/{eventname}")

  public ResponseEntity<List<Photo>> getEventsByOrganizerId(@PathVariable String eventname) {
    Event event = eventRepository.findByEventname(eventname);
    List<Photo> photos = photoRepository.findByEvent(event);
      if (photos.isEmpty()) {
          return ResponseEntity.notFound().build();
      } else {
          return ResponseEntity.ok(photos);
        }
    }


    @GetMapping("/api/photos")

    public ResponseEntity<List<Photo>> getAllPhotos() {
 
      List<Photo> photos = photoRepository.findAll();
        if (photos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(photos);
          }
      }


      @DeleteMapping("/api/photos/{photoid}")
    public ResponseEntity<String> deletePhoto(@PathVariable String photoid) {
        try {
            photoRepository.deleteById(photoid);
            return ResponseEntity.ok("Photo deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete photo");
        }
    }

}
