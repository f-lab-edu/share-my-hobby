package flab.project.sharemyhobby.exception.user;

public class LoginRequiredException extends RuntimeException {

    public LoginRequiredException() {
        super("User has not yet logged in");
    }

}
