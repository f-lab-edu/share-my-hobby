package flab.project.sharemyhobby.exception.hobby;

import flab.project.sharemyhobby.model.api.request.hobby.LikeHobbyRequest;

import java.text.MessageFormat;

public class DuplicateLikeHobbyException extends RuntimeException {

    public DuplicateLikeHobbyException(LikeHobbyRequest likeHobbyRequest, Long userId) {
        this(MessageFormat.format("User id [{0}] has already registered hobbies in this list : {1}", userId, likeHobbyRequest));
    }

    public DuplicateLikeHobbyException(String message) {
        super(message);
    }

}
