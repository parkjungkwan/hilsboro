package hillsboro.philoarte.scalar.advices;

import hillsboro.philoarte.scalar.components.MessengerVo;
import hillsboro.philoarte.scalar.enums.ErrorCode;
import hillsboro.philoarte.scalar.exceptions.LoginRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@Slf4j // 로그안의 e의 내용을 출력
@ControllerAdvice
public class SecurityExceptionAdvice {
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<MessengerVo> handleRuntimeException(RuntimeException e) {
        log.info("handleRuntimeException", e);

        MessengerVo response = MessengerVo.builder().code("test").message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SecurityException.class)
    protected ResponseEntity<MessengerVo> handleCustomAuthenticationException(SecurityException e) {
        log.info("handleCustomAuthenticationException", e);

        MessengerVo response = MessengerVo.builder().code(ErrorCode.AUTHENTICATION_FAILED.getCode()).message(e.getMessage())
                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LoginRuntimeException.class)
    protected ResponseEntity<MessengerVo> handleLoginFailedException(LoginRuntimeException e) {
        log.info("handleLoginFailedException", e);

        MessengerVo response = MessengerVo.builder().code(ErrorCode.LOGIN_FAILED.getCode()).message(e.getMessage())
                .status(ErrorCode.LOGIN_FAILED.getStatus()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<MessengerVo> handleBadCredentialsException(LoginRuntimeException e) {
        log.info("handleBadCredentialsException", e);

        MessengerVo response = MessengerVo.builder().code(ErrorCode.LOGIN_FAILED.getCode()).message(e.getMessage())
                .status(ErrorCode.LOGIN_FAILED.getStatus()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<MessengerVo> handleAccessDeniedException(AccessDeniedException e) {
        log.info("handleAccessDeniedException", e);

        MessengerVo response = MessengerVo.builder().code(ErrorCode.ACCESS_DENIED.getCode())
                .message(ErrorCode.ACCESS_DENIED.getMessage()).status(ErrorCode.ACCESS_DENIED.getStatus()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    protected ResponseEntity<MessengerVo> handleInsufficientAuthenticationException(AccessDeniedException e) {
        log.info("handleInsufficientAuthenticationException", e);

        MessengerVo response = MessengerVo.builder().code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ErrorCode.AUTHENTICATION_FAILED.getMessage())
                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}