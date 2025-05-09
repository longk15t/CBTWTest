package ctflearn.exceptions;

public class InvalidBrowserNameException extends IllegalStateException {
    public InvalidBrowserNameException(String msg) {
        super(msg);
    }
}
