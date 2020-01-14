package com.example.grad1.controller.security;
import com.example.grad1.domain.User;
import com.example.grad1.to.userTo.UserTo;
import com.example.grad1.to.userTo.UserUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
        private static final long serialVersionUID = 1L;

        private UserTo userTo;

        public AuthorizedUser(User user) {
                super(user.getEmail(),
                        user.getPassword(),
                        user.isEnabled(), //enabled
                        user.isEnabled(), //accountNonExpired
                        user.isEnabled(), //credentialsNonExpired
                        user.isEnabled(), //accountNonLocked
                        user.getRoles());
                this.userTo = UserUtil.asTo(user);
        }

        public int getId() {
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
