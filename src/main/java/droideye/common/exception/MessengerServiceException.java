package droideye.common.exception;

import java.io.Serializable;

public class MessengerServiceException extends Exception implements Serializable {


    private static final long serialVersionUID = 5835885530008840372L;

    public MessengerServiceException() {
        super();
    }

    public MessengerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessengerServiceException(String message) {
        super(message);
    }

    public MessengerServiceException(Throwable cause) {
        super(cause);
    }
}
