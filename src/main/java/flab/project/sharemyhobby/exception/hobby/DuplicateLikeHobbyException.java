package flab.project.sharemyhobby.exception.hobby;

public class DuplicateLikeHobbyException extends RuntimeException {

    public DuplicateLikeHobbyException() {
        super("Hobbies the user has already chosen are included");
    }

}
