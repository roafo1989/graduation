package com.example.grad1.service.user;
import com.example.grad1.controller.security.AuthorizedUser;
import com.example.grad1.domain.User;
import com.example.grad1.repository.UserRepository;
import com.example.grad1.to.userTo.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.example.grad1.to.userTo.UserUtil.prepareToSave;
import static com.example.grad1.util.ValidationUtil.checkNotFound;
import static com.example.grad1.util.ValidationUtil.checkNotFoundWithId;


@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }


    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void update(UserTo user) {

    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = repository.getByEmail(email.toLowerCase());
        System.out.println("service: " + user.getName() + "; " + user.getRoles());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);

    }
}

