package flab.project.sharemyhobby.controller.user;

import flab.project.sharemyhobby.annotaion.LoginUserId;
import flab.project.sharemyhobby.model.api.request.user.DeleteRequest;
import flab.project.sharemyhobby.model.api.request.user.JoinRequest;
import flab.project.sharemyhobby.model.api.request.user.LoginRequest;
import flab.project.sharemyhobby.model.api.request.user.PasswordRequest;
import flab.project.sharemyhobby.model.api.response.user.DeleteResponse;
import flab.project.sharemyhobby.model.api.response.user.JoinResponse;
import flab.project.sharemyhobby.model.api.response.user.LoginResponse;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import flab.project.sharemyhobby.service.user.LoginService;
import flab.project.sharemyhobby.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    private final LoginService loginService;

    @PostMapping(path = "/join")
    public JoinResponse join(@RequestBody JoinRequest joinRequest) {
        User user = userService.join(new Email(joinRequest.getEmail()),  joinRequest.getNickname(), joinRequest.getPassword());
        return JoinResponse.from(user);
    }

    @GetMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        User user = loginService.login(new Email(loginRequest.getEmail()), loginRequest.getPassword());
        return LoginResponse.from(user);
    }

    @GetMapping(path = "/logout")
    public void logout() {
        loginService.logout();
    }

    @DeleteMapping(path = "/account")
    public DeleteResponse delete(@RequestBody DeleteRequest deleteRequest, @LoginUserId Long userId) {
        User user = userService.deleteUser(userId, deleteRequest.getPassword());
        return DeleteResponse.from(user);
    }

    @PutMapping(path = "/account/password")
    public void updatePassword(@Valid @RequestBody PasswordRequest passwordRequest, @LoginUserId Long userId) {
        userService.updatePassword(userId, passwordRequest);
    }

}
