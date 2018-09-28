package droideye.common.exception;

import java.io.Serializable;

public class DataAccessException extends Exception implements Serializable {


    private static final long serialVersionUID = -434897114933338234L;

    public DataAccessException() {
        super();
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }

}