package flab.project.sharemyhobby.exception;

public class AlreadyLoginException extends RuntimeException {
    public AlreadyLoginException() {
        super("This user has already logged in");
    }
}
