package flab.project.sharemyhobby.model.api.request.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class PasswordRequest {

    @NotEmpty
    private final String oldPassword;

    @NotEmpty
    private final String newPassword;

}
