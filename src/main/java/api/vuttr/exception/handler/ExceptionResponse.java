package api.vuttr.exception.handler;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;

    private Date timestamp;

    private String uri;

    public ExceptionResponse(String message, Date timestamp, String uri) {
        this.message = message;
        this.timestamp = timestamp;
        this.uri = uri;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getUri() {
        return uri;
    }
}
