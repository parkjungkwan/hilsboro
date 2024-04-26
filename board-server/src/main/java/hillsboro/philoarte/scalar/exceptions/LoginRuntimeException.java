package hillsboro.philoarte.scalar.exceptions;

import hillsboro.philoarte.scalar.enums.ErrorCode;

public class LoginRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoginRuntimeException() {
        super(ErrorCode.LOGIN_FAILED.getMessage());
    }

    public LoginRuntimeException(String message) {
        super(message);
    }

}
