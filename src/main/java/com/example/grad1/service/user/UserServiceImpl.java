package com.example.grad1.service.user;/*
package com.example.lvsystem.service.user;

import com.example.lvsystem.AuthorizedUser;
import com.example.lvsystem.domain.User;
import com.example.lvsystem.repository.UserRepository;
import com.sun.xml.fastinfoset.QualifiedName;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.example.lvsystem.util.ValidationUtil.checkNotFound;
import static com.example.lvsystem.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    private static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    @Override
    public User create(@NotNull User user) {
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public User getByEmail(@NotBlank String email) {
        return checkNotFound(repository.getByEmail(email), "email = " + email);
    }

    @Override
    public List<User> getAllWithLunches() {
        return repository.findAll();
    }

    @Override
    public void update(@NotNull User user) {
        User updated = get(user.getId());
        user.setEnabled(updated.isEnabled());
        user.setRegistered(updated.getRegistered());
        prepareToSave(user, passwordEncoder);
        // to allow update user without submitting password
        if (!StringUtils.hasText(user.getPassword())) {
            user.setPassword(updated.getPassword());
        }
        repository.save(user);
    }

    @Override
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
*/
