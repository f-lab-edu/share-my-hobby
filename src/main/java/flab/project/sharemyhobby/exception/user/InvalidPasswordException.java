package flab.project.sharemyhobby.exception.user;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Invalid password has entered");
    }

}
