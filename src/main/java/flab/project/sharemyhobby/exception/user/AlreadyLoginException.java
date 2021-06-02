package flab.project.sharemyhobby.exception.user;

public class AlreadyLoginException extends RuntimeException {

    public AlreadyLoginException() {
        super("This user has already logged in");
    }

}
