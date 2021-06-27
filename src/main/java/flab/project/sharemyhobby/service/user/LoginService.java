package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;

public interface LoginService {

    User login(Email email, String password);

    void logout();

    Long getLogInUserId();

}
