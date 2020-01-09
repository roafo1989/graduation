package com.example.grad1.controller.restaurant;

import com.example.grad1.domain.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

import static com.example.grad1.controller.restaurant.ProfileRestaurantController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/restaurants";

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllForId(@PathVariable("id") int id, @RequestParam String date) {
        return super.get(getDate(date), id);
    }

    @GetMapping(value = "/by-date",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllForDate(@RequestParam String date) {
        return super.getAllWithLunches(getDate(date));
    }

    @GetMapping(value = "/today",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllForToday() {
        return super.getAllWithLunches(LocalDate.now());
    }

    private static LocalDate getDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate == null ? LocalDate.now() : localDate;
    }

}
