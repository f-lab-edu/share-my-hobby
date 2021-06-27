package flab.project.sharemyhobby.exception.user;

public class DuplicateProfileException extends RuntimeException {

    public DuplicateProfileException() {
        super("Profile already exists");
    }

}