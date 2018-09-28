package droideye.common.exception;

import java.io.Serializable;

public class MemberServiceException extends Exception implements Serializable {

    private static final long serialVersionUID = -6166533554682206492L;

    public MemberServiceException() {
        super();
    }

    public MemberServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberServiceException(String message) {
        super(message);
    }

    public MemberServiceException(Throwable cause) {
        super(cause);
    }
}
