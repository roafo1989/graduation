package com.example.grad1.controller.voice;

import com.example.grad1.controller.security.AuthorizedUser;
import com.example.grad1.domain.Restaurant;
import com.example.grad1.domain.User;
import com.example.grad1.domain.Voice;
import com.example.grad1.service.voice.VoiceService;
import com.example.grad1.to.voiceTo.VoiceTo;
import com.example.grad1.to.voiceTo.VoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.example.grad1.controller.voice.VoiceUserController.REST_URL;


@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiceUserController {

    static final String REST_URL = "/rest/voices";

    @Autowired
    VoiceService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoiceTo> create(@RequestParam ("restaurantId") int restaurantId, @AuthenticationPrincipal AuthorizedUser authorizedUser){
        VoiceTo created = VoiceUtil.asTo(service.create(authorizedUser.getId(),restaurantId));
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
//By User
    @GetMapping("/my-voice-today")
    public VoiceTo getSelfToday(@AuthenticationPrincipal AuthorizedUser authorizedUser){
        return VoiceUtil.asTo(service.getByUserId(authorizedUser.getId(), LocalDateTime.now()));
    }
    @GetMapping("/my-history")
    public List<VoiceTo> getSelfHistory(@AuthenticationPrincipal AuthorizedUser authorizedUser){
        return service.getAllByUserId(authorizedUser.getId());
    }

//By Restaurant
    @GetMapping("/by-restaurant-today")
    public List<VoiceTo> current(@RequestParam int restaurantId) {
        return service.getByRestaurantIdAndDate(LocalDateTime.now(),restaurantId);
    }

    @GetMapping("/by-restaurant-history-between-dates")
    public List<VoiceTo> getRestaurantHistory(@RequestParam String start, @RequestParam String end, @RequestParam int restaurantId){
        return service.getByRestaurantBetweenDates(start, end,restaurantId);
    }
    @GetMapping("/by-restaurant-history-from-date")
    public List<VoiceTo> getRestaurantHistory(@RequestParam String start, @RequestParam int restaurantId){
        return service.getByRestaurantBetweenDates(start, restaurantId);
    }
    @GetMapping("/by-restaurant-history")
    public List<VoiceTo> getRestaurantHistory(@RequestParam int restaurantId){
        return service.getByRestaurantId(restaurantId);
    }

//Summary
    @GetMapping("/rating-by")
    public Map<String, Integer> getRatingBy(@RequestParam String date){
        LocalDateTime dateTime = LocalDate.parse(date).atStartOfDay();
        return service.getRatingByDate(dateTime);
    }
    @GetMapping("/rating-today")
    public Map<String, Integer> getRatingToday(){
        return service.getRatingByDate(LocalDateTime.now());
    }

    @GetMapping("/rating")
    public Map<String, Integer> getRating(){
        return service.getRating();
    }

    @GetMapping("/all-for-today")
    public List<VoiceTo> getAllForToday(){
        return service.getAllByDate(LocalDateTime.now());
    }

    @GetMapping("/summary-today")
    public Map<Restaurant, List<VoiceTo>> getAll(){
        return service.getAllByRestaurantIdAndDate(LocalDateTime.now());
    }
}
