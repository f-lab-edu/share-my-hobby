package flab.project.sharemyhobby.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super("User already exists");
    }

}
