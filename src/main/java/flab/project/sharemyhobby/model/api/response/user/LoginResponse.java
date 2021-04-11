package flab.project.sharemyhobby.model.api.response.user;

import flab.project.sharemyhobby.model.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class LoginResponse {

    private final User user;

}
