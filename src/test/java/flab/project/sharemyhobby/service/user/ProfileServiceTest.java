package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.aws.S3Uploader;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.Profile;
import flab.project.sharemyhobby.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@SpringBootTest
class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    private MultipartFile profileImage;

    User user;

    private MultipartFile getImageFile(String fileName) throws IOException {
        URL testImageUrl = getClass().getResource(fileName);
        File testImage = new File(testImageUrl.getFile());
        return new MockMultipartFile(fileName, new FileInputStream(testImage));
    }

    @BeforeAll
    void setUp() throws IOException {
        profileImage = getImageFile("/test_profile.jpg");
        user = userService.join(new Email("adorno10@naver.com"), "cold-pumpkin", "12345678");
    }

    @Test
    @DisplayName("프로필을 등록하면 DB에 저장된 프로필 정보를 리턴한다")
    void testRegisterProfileAndReturnProfileInfo() {
        String statusMessage = "아..배고프다";
        Profile profile = profileService.registerProfile(1L, profileImage, statusMessage);

        assertThat(profile.getId()).isNotNull();
        assertThat(profile.getUserId()).isEqualTo(1L);
        assertThat(profile.getProfileImageUrl()).isNotNull();
        assertThat(profile.getStatusMessage()).isEqualTo(statusMessage);

        log.info("프로필 등록 : {}", profile);
    }

}