package flab.project.sharemyhobby.exception.user;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super("User already exists");
    }

}
