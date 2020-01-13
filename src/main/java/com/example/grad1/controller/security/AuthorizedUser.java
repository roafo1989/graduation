package com.example.grad1.controller.security;
import com.example.grad1.domain.User;
import com.example.grad1.to.UserTo;



public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
        private static final long serialVersionUID = 1L;

        private UserTo userTo;

        public AuthorizedUser(User user) {
                super(user.getEmail(), user.getPassword(), user.getRoles());
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
