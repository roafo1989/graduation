package com.example.grad1.service.user;

import com.example.grad1.domain.User;
import com.example.grad1.to.userTo.UserTo;
import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    User update(User user);

    User update(UserTo user);

    List<User> getAll();
    void sendMail(User user);
}
