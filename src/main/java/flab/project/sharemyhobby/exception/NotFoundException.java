package flab.project.sharemyhobby.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Email address not found");
    }

}
