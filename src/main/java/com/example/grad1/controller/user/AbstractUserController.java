package com.example.grad1.controller.user;

import com.example.grad1.domain.User;
import com.example.grad1.service.user.UserService;
import com.example.grad1.to.UserTo;
import com.example.grad1.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.grad1.util.ValidationUtil.assureIdConsistent;
import static com.example.grad1.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {

    @Autowired
    protected UserService service;

    public List<User> getAll() {
        return service.getAll();
    }

    public User get(int id) {
        return service.get(id);
    }

    public User create(UserTo userTo) {
        return create(UserUtil.createNewFromTo(userTo));
    }

    public User create(User user) {
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public void update(User user, int id) {
        assureIdConsistent(user, id);
        service.update(user);
    }

/*    public void update(UserTo userTo, int id) {
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }*/

    User getByEmail(String email) {
        return service.getByEmail(email);
    }
}
