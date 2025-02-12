package cn.daenx.myadmin.framework.common.exception;

/**
 * 未登录异常
 */
public class LoginException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
