package com.example.grad1.service.user;
import com.example.grad1.controller.security.AuthorizedUser;
import com.example.grad1.domain.User;
import com.example.grad1.repository.UserRepository;
import com.example.grad1.to.userTo.UserTo;
import com.example.grad1.util.exception.NotFoundActivationException;
import org.hsqldb.lib.StringUtil;
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
import org.springframework.util.StringUtils;

import javax.mail.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

import static com.example.grad1.to.userTo.UserUtil.prepareToSave;
import static com.example.grad1.to.userTo.UserUtil.updateFromTo;
import static com.example.grad1.util.ValidationUtil.checkNotFound;
import static com.example.grad1.util.ValidationUtil.checkNotFoundWithId;


@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private MailSender mailSender;
    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, MailSender mailSender) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

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
        user.setEnabled(false);
        user.setActivation(UUID.randomUUID().toString());
        repository.save(prepareToSave(user, passwordEncoder));

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to vote manager! Your link for activation: http://localhost:8080/rest/profile/activate/%s",
                    user.getName(), user.getActivation()
            );
            mailSender.send(user.getEmail(),"Activation Code",message);
        }

        return repository.getByEmail(user.getEmail());
    }

    @Override
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
        return repository.getOne(user.getId());
    }

    @Override
    public User update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
        return repository.getOne(user.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
    @Override
    public User activateUser(String code) {
        User user = repository.findByActivation(code);
        if (user == null){
            throw new NotFoundActivationException("User not found. Incorrect activation code");
        }
        user.setActivation(null);
        user.setEnabled(true);
        repository.save(user);
        return user;
    }
}

