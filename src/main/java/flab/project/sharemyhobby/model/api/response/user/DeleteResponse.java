package flab.project.sharemyhobby.model.api.response.user;

import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class DeleteResponse {

    private Email email;

    private String nickname;

    public DeleteResponse(Email email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static DeleteResponse from(User user) {
        return new DeleteResponse(user.getEmail(), user.getNickname());
    }

}
