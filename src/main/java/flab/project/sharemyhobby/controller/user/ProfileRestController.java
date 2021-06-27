package flab.project.sharemyhobby.controller.user;

import flab.project.sharemyhobby.annotaion.LoginUserId;
import flab.project.sharemyhobby.model.user.Profile;
import flab.project.sharemyhobby.service.user.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/my-infos")
public class ProfileRestController {

    private final ProfileService profileService;

    @PostMapping(path = "/profile")
    public Profile register(MultipartFile profileImage, String statusMessage, @LoginUserId Long userId) {
        return profileService.registerProfile(userId, profileImage, statusMessage);
    }

    @PutMapping(path = "/profile")
    public Profile update(MultipartFile profileImage, String statusMessage, @LoginUserId Long userId) {
        return profileService.updateProfile(userId, profileImage, statusMessage);
    }

}
