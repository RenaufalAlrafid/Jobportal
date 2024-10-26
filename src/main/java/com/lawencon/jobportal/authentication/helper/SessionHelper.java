package com.lawencon.jobportal.authentication.helper;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;
import com.lawencon.jobportal.authentication.model.UserPrinciple;
import com.lawencon.jobportal.persistence.entity.User;

public class SessionHelper {
    public static User getLoginUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserPrinciple) {
            return ((UserPrinciple) authentication.getPrincipal()).getUser();
        } else {
            return null;
        }
    }

    public static UserPrinciple getUserPrinciple() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }

    public static void validateRole(String code) {
        User user = getLoginUser();
        if (getLoginUser() == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "You must login first");
        }
        String role = user.getRole().getCode();

        if (!role.equals(code)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "You are not authorized to access this resource");
        }
    }
}
