package com.example.grad1.controller.admin;

import com.example.grad1.domain.Restaurant;
import com.example.grad1.service.voice.VoiceService;
import com.example.grad1.to.voiceTo.VoiceTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.example.grad1.controller.admin.VoiceAdminController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiceAdminController {

    static final String REST_URL = "/rest/admin/voices";

    @Autowired
    VoiceService service;

    //By Restaurant
    @GetMapping("/restaurant-by")
    public List<VoiceTo> getByRestaurant(@RequestParam(value = "start", required = false) String start,
                                         @RequestParam(value = "end", required = false) String end,
                                         @RequestParam(value = "date", required = false) String date,
                                         @RequestParam int restaurantId){
        if(start != null && end != null){
            return service.getByRestaurantBetweenDates(start, end, restaurantId);
        } else return service.getByRestaurantIdAndDate(getStringDate(date), restaurantId);
    }
    @GetMapping("/restaurant")
    public List<VoiceTo> getByRestaurant(@RequestParam int restaurantId){
        return service.getByRestaurantId(restaurantId);
    }

    @GetMapping("/all-by")
    public List<VoiceTo> getAllByDate(@RequestParam(value = "date", required = false) String date){
        return service.getAllByDate(getStringDate(date));
    }
    @GetMapping("/all")
    public List<VoiceTo> getAll(){
        return service.getAll();
    }

    @GetMapping("/summary-by")
    public Map<Restaurant, List<VoiceTo>> getSummary(@RequestParam(value = "date", required = false) String date){
        return service.getAllByRestaurantIdAndDate(getStringDate(date));
    }
    @GetMapping("/summary")
    public Map<Restaurant, List<VoiceTo>> getSummary(){
        return service.getAllByRestaurantId();
    }

    public String getStringDate(String date){
        if (date == null){
            date = LocalDate.now().toString();
        }
        return date;
    }
}
