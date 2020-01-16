package com.example.grad1.service.user;

import com.example.grad1.Grad1Application;
import com.example.grad1.domain.Role;
import com.example.grad1.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static com.example.grad1.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Grad1Application.class)
class UserServiceImplTest {

    @Autowired
    protected UserService service;

    @Test
    void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN, USER2);
    }

    @Test
    void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, USER, ADMIN, USER2);
    }

    @Test
    void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    void getByEmail() {
        User user = service.getByEmail("user@mail.ru");
        assertMatch(user, USER);
        User admin = service.getByEmail("admin@mail.ru");
        assertMatch(admin, ADMIN);
    }

    @Test
    void create() throws Exception {
        User newUser = new User(null, "New", "mail@mail.ru", "newPass", Role.ROLE_USER);
        User created = service.create(new User(newUser));
        newUser.setId(created.getId());
        assertMatch(newUser, created);
    }

    @Test
    void update() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

}