package com.example.grad1.controller.user;

import com.example.grad1.controller.security.AuthorizedUser;
import com.example.grad1.service.voice.VoiceService;
import com.example.grad1.to.voiceTo.VoiceTo;
import com.example.grad1.to.voiceTo.VoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.example.grad1.controller.user.VoiceUserController.REST_URL;


@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiceUserController {

    static final String REST_URL = "/rest/voices";

    @Autowired
    VoiceService service;

    @PostMapping
    public ResponseEntity<VoiceTo> create(@RequestParam ("restaurantId") int restaurantId,
                                          @AuthenticationPrincipal AuthorizedUser authorizedUser){
        VoiceTo created = VoiceUtil.asTo(service.create(authorizedUser.getId(),restaurantId));
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
    @PutMapping
    public VoiceTo update(@RequestParam ("restaurantId") int restaurantId,
                          @AuthenticationPrincipal AuthorizedUser authorizedUser){
        return VoiceUtil.asTo(service.create(authorizedUser.getId(),restaurantId));
    }


    @GetMapping
    public List<VoiceTo> getSelf(@AuthenticationPrincipal AuthorizedUser authorizedUser,
                                        @RequestParam(value = "date", required = false) String date){
        if(date == null) {
            return service.getAllByUserId(authorizedUser.getId());
        } else return service.getByUserIdAndDate(authorizedUser.getId(), date);
    }

//Summary
    @GetMapping("/rating-by")
    public Map<String, Integer> getRatingBy(@RequestParam(value = "date", required = false) String date){
        return service.getRatingByDate(getStringDate(date));
    }

    @GetMapping("/rating")
    public Map<String, Integer> getRating(){
        return service.getRating();
    }

    public String getStringDate(String date){
        if (date == null){
            date = LocalDate.now().toString();
        }
        return date;
    }
}
