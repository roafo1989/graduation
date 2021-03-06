package com.example.grad1.controller.user;

import com.example.grad1.controller.base.AbstractUserController;
import com.example.grad1.controller.security.AuthorizedUser;
import com.example.grad1.domain.User;
import com.example.grad1.to.userTo.UserTo;
import com.example.grad1.to.userTo.UserUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.example.grad1.controller.user.ProfileUserController.REST_URL;

@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileUserController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserTo userTo) {
        User created = super.create(userTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.get(authorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User update(@RequestParam (required = false) String name,
                       @RequestParam (required = false) String email,
                       @RequestParam (required = false) String password,
                       @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        UserTo userTo = UserUtil.asTo(super.get(authorizedUser.getId()));
        if(name != null) {userTo.setName(name);}
        if(email != null) {userTo.setEmail(email);}
        if(password != null) {userTo.setPassword(password);}
        return super.update(userTo, authorizedUser.getId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.delete(authorizedUser.getUserTo().getId());
    }


    @GetMapping(value = "/activate/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public User activate(@PathVariable String code) {
        return service.activateUser(code);
    }

}
