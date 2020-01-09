package com.example.grad1.controller.lunch;

import com.example.grad1.domain.Lunch;
import com.example.grad1.service.lunch.LunchService;
import com.example.grad1.to.model.LunchTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.example.grad1.controller.lunch.LunchController.REST_URL;
import static com.example.grad1.util.ValidationUtil.assureIdConsistent;
import static com.example.grad1.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = REST_URL,produces = MediaType.APPLICATION_JSON_VALUE)
public class LunchController {
    public static final String REST_URL = "/rest/admin/restaurants";

    private LunchService lunchService;
    @Autowired
    public LunchController(LunchService lunchService) {
        this.lunchService = lunchService;
    }

    @GetMapping(value = "/{restId}/lunches", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<LunchTo> getAllByRest(@PathVariable("restId") int restaurantId) {
        return lunchService.getAll(restaurantId);
    }

    @GetMapping(value = "{restId}/lunches/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Lunch get(@PathVariable("restId") int restId, @PathVariable("id") int id) {
        return lunchService.get(id, restId);
    }

    @DeleteMapping("{restId}/lunches/{lunchId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restId") int restId, @PathVariable("lunchId") int lunchId) {
        lunchService.delete(lunchId, restId);
    }

    @PutMapping(value = "/{restId}/lunches/{lunchId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Lunch update(@RequestBody Lunch lunch, @PathVariable("restId") int restId, @PathVariable("lunchId") int lunchId) {
        assureIdConsistent(lunch,lunchId);
        return lunchService.update(lunch, restId);
    }

    @PostMapping(value = "/{restId}/lunches", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lunch> save(@RequestBody Lunch lunch, @PathVariable("restId") int restId) {
        checkNew(lunch);
        Lunch created = lunchService.create(lunch, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/" + "lunches/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
