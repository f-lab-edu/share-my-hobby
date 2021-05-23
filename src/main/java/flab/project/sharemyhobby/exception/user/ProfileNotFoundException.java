package flab.project.sharemyhobby.exception.user;

public class ProfileNotFoundException extends RuntimeException {
    
    public ProfileNotFoundException() {
        super("User profile not found");
    }

}
