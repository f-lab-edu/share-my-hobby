package flab.project.sharemyhobby.exception.user;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Email address not found");
    }

}
