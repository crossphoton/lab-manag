package lab.management.Errors;

public class HTTPError extends Throwable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Integer code;
    public String message;


    public HTTPError(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
