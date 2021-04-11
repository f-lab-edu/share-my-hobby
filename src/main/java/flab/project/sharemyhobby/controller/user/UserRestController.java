package flab.project.sharemyhobby.controller.user;

import flab.project.sharemyhobby.model.api.request.user.JoinRequest;
import flab.project.sharemyhobby.model.api.request.user.LoginRequest;
import flab.project.sharemyhobby.model.api.response.user.JoinResponse;
import flab.project.sharemyhobby.model.api.response.user.LoginResponse;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import flab.project.sharemyhobby.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping(path = "/join")
    public JoinResponse join(@RequestBody JoinRequest joinRequest) {
        User user = userService.join(new Email(joinRequest.getEmail()),  joinRequest.getNickname(), joinRequest.getPassword());
        return new JoinResponse(user);
    }

    @GetMapping(path = "/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(new Email(loginRequest.getEmail()), loginRequest.getPassword());
        return new LoginResponse(user);
    }

}
