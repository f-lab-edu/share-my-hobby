package flab.project.sharemyhobby.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("You have entered an invalid password");
    }
}