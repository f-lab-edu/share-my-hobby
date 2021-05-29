package flab.project.sharemyhobby.model.api.request.user;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class JoinRequest {

    private String email;

    private String nickname;

    private String password;

}
