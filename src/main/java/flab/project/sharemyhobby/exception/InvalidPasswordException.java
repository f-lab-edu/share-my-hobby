package flab.project.sharemyhobby.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Invalid password has entered");
    }

}
