package com.example.grad1.controller.user;

import com.example.grad1.controller.base.AbstractRestaurantController;
import com.example.grad1.domain.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

import static com.example.grad1.controller.user.UserRestaurantController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/restaurants";

    @GetMapping(value = "/by-date",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllForDate(@RequestParam String date) {
        return super.getAllWithLunches(getDate(date));
    }

    @GetMapping(value = "/today",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllForToday() {
        return super.getAllWithLunches(LocalDate.now());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable int id) {
        return super.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }



    private static LocalDate getDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate == null ? LocalDate.now() : localDate;
    }
}
