package com.example.grad1.controller.voice;

import com.example.grad1.domain.Restaurant;
import com.example.grad1.service.voice.VoiceService;
import com.example.grad1.to.voiceTo.VoiceTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


import static com.example.grad1.controller.security.SecurityUtil.authUserId;
import static com.example.grad1.controller.voice.VoiceUserController.REST_URL;


@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiceUserController {

    static final String REST_URL = "/rest/voices";

    @Autowired
    VoiceService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoiceTo> create(@RequestParam ("restaurantId") int restaurantId){
        VoiceTo created = service.create(authUserId(),restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
//By User
    @GetMapping("/my-voice-today")
    public VoiceTo getSelfToday(){
        return service.getByUserId(authUserId(), LocalDateTime.now());
    }
    @GetMapping("/my-history")
    public List<VoiceTo> getSelfHistory(){
        return service.getAllByUserId(authUserId());
    }

//By Restaurant
    @GetMapping("/by-restaurant-today")
    public List<VoiceTo> current(@RequestParam int restaurantId) {
        return service.getByRestaurantIdAndDate(LocalDateTime.now(),restaurantId);
    }

    @GetMapping("/by-restaurant-history")
    public List<VoiceTo> getRestaurantHistory(@RequestParam String start, @RequestParam String end, @RequestParam int restaurantId){
        return service.getByRestaurantBetweenDates(start, end,restaurantId);
    }

//Summary
    @GetMapping("/all-for-today")
    public List<VoiceTo> getAllForToday(){
        return service.getAllByDate(LocalDateTime.now());
    }

    @GetMapping("/summary-today")
    public Map<Restaurant, List<VoiceTo>> getAll(){
        return service.getAllByRestaurantIdAndDate(LocalDateTime.now());
    }

    @GetMapping("/rating-today")
    public Map<String, Integer> getRating(){
        return service.getRatingByDate(LocalDateTime.now());
    }

}
