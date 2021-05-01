package flab.project.sharemyhobby.exception;

public class LoginRequiredException extends RuntimeException {

    public LoginRequiredException() {
        super("User has not yet logged in");
    }

}
