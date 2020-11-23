package lab.management.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class NotAllowed extends HTTPError {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotAllowed(){
        super(406, "Not Acceptable");
    }
}
