package uhk.project.webcontacts.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uhk.project.webcontacts.backend.system.Result;
import uhk.project.webcontacts.backend.system.StatusCode;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleAuthenticationException(Exception ex) {
        return new Result(false, StatusCode.UNAUTHORIZED, "username or password is incorrect.", ex.getMessage());
    }
}
