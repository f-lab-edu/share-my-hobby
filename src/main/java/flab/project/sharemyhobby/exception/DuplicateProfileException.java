package flab.project.sharemyhobby.exception;

public class DuplicateProfileException extends RuntimeException {

    public DuplicateProfileException() {
        super("Profile already exists");
    }

}