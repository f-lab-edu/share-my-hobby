package flab.project.sharemyhobby.controller.hobby;

import flab.project.sharemyhobby.model.hobby.HobbyInfo;
import flab.project.sharemyhobby.service.hobby.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
