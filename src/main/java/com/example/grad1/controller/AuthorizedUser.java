package com.example.grad1.controller;


import com.example.grad1.domain.User;
import com.example.grad1.to.UserTo;
import com.example.grad1.util.UserUtil;

import javax.persistence.criteria.CriteriaBuilder;


public class AuthorizedUser extends User{
private static final long serialVersionUID = 1L;

private UserTo userTo;

/*public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.userTo = UserUtil.asTo(user);
        }*/

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
