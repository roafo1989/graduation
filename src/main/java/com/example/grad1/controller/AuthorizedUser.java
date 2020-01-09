package com.example.grad1.controller;


import com.example.grad1.domain.User;
import com.example.grad1.to.converter.UserUtil;
import com.example.grad1.to.model.UserTo;


public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
private static final long serialVersionUID = 1L;

private UserTo userTo;

public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.userTo = UserUtil.asTo(user);
        }

public Integer getId() {
        return userTo.getId();
        }

public void update(UserTo newTo) {
        userTo = newTo;
        }

public UserTo getUserTo() {
        return userTo;
        }

@Override
public String toString() {
        return userTo.toString();
        }

}
