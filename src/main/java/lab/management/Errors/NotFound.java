package lab.management.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFound extends HTTPError {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotFound() {
        super(404, "Not found");
    }
}
