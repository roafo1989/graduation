package com.example.grad1.util;

import com.example.grad1.domain.User;
import com.example.grad1.domain.baseModel.AbstractBaseEntity;
import com.example.grad1.domain.baseModel.HasId;
import com.example.grad1.repository.UserRepository;
import com.example.grad1.util.exception.IllegalRequestDataException;
import com.example.grad1.util.exception.NotFoundException;
import com.example.grad1.util.exception.VoteTimeViolationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

public class ValidationUtil {
    private static LocalTime DEADLINE_TIME = LocalTime.of(11, 00);
    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id = " + id);
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id = " + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id = null)");
        }
    }


    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalRequestDataException(bean + " must be with id = " + id);
        }
    }

    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static void checkTimeForOperations(LocalTime time) {
        if (time.isAfter(DEADLINE_TIME)) {
            throw new VoteTimeViolationException("Voting is over for today");
        }
    }

    public static LocalTime getDeadLineTime() {
        return DEADLINE_TIME;
    }

    public static void setDeadLineTime(LocalTime time) {
        DEADLINE_TIME = time;
    }



    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    public static Throwable logAndGetRootCause(Logger log, HttpServletRequest req, Exception e, boolean logException) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logException) {
            log.error("Exception at request {}: {}", req.getRequestURL(), rootCause);
        } else {
            log.warn("Error at request  {}: {}", req.getRequestURL(), rootCause.toString());
        }
        return rootCause;
    }

}