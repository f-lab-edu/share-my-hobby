package flab.project.sharemyhobby.controller.user;

import flab.project.sharemyhobby.model.api.request.user.JoinRequest;
import flab.project.sharemyhobby.model.api.response.user.JoinResponse;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import flab.project.sharemyhobby.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @PostMapping(path = "/join")
    public ResponseEntity<JoinResponse> join(@RequestBody JoinRequest joinRequest) {
        User user = userService.join(new Email(joinRequest.getEmail()),  joinRequest.getNickname(), joinRequest.getPassword());
        return ResponseEntity.ok(new JoinResponse(user));
    }

}
