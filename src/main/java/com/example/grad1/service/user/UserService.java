package com.example.grad1.service.user;

import com.example.grad1.domain.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

    User create(@NotNull User user);

    void delete(int id);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    User get(int id);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    User getByEmail(@NotBlank String email);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    List<User> getAll();

    void update(@NotNull User user);

    void enable(int id, boolean enabled);
}
