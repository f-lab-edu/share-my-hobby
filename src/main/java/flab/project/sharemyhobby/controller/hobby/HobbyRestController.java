package flab.project.sharemyhobby.controller.hobby;

import flab.project.sharemyhobby.annotaion.LoginUserId;
import flab.project.sharemyhobby.model.api.request.hobby.LikeHobbyRequest;
import flab.project.sharemyhobby.model.hobby.HobbyInfo;
import flab.project.sharemyhobby.service.hobby.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobbies")
public class HobbyRestController {

    private final HobbyService hobbyService;

    @GetMapping("/hobby-list")
    public List<HobbyInfo> getAllHobby() {
        return hobbyService.findAllHobbyInfo();
    }

    @PostMapping("/likes")
    public List<HobbyInfo> registerLikeHobby(@Valid @RequestBody LikeHobbyRequest likeHobbyRequest, @LoginUserId Long userId) {
        return hobbyService.registerLikeHobby(likeHobbyRequest, userId);
    }

}
