package com.example.grad1.controller;

import lombok.NoArgsConstructor;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor
public class SecurityUtil {
   /* public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }*/

    public static int authUserId() {
       // return get().getUserTo().getId();
        return 100000;
    }
}
