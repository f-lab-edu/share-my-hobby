package flab.project.sharemyhobby.exception;

public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException() {
        super("User profile not found");
    }

}
