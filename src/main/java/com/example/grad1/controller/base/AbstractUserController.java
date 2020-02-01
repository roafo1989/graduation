package com.example.grad1.controller.base;

import com.example.grad1.domain.User;
import com.example.grad1.service.user.UserService;
import com.example.grad1.to.userTo.UserTo;
import com.example.grad1.to.userTo.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

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

    public User update(User user, int id) {
        assureIdConsistent(user, id);
        return service.update(user);
    }

    public User update(UserTo userTo, int id) {
        assureIdConsistent(userTo, id);
        return service.update(userTo);
    }

    public User getByEmail(String email) {
        return service.getByEmail(email);
    }

}
