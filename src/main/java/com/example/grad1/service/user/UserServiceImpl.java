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

import javax.mail.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Properties;


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
        sendMail(user);
        return repository.save(prepareToSave(user, passwordEncoder));
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
    public void sendMail(User user){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smpt.mail.ru");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.sendpartial", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("roafo@mail.ru", "shevchuk1");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("roafo@mail.ru"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Подтверждение регистрации");
            message.setText("Привет, " + user.getName() + "! Вы успешно зарегистрированы." );
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

