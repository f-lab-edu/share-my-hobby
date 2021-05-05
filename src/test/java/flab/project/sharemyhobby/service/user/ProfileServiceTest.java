package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.exception.FileUploadException;
import flab.project.sharemyhobby.mapper.user.ProfileMapper;
import flab.project.sharemyhobby.model.user.Profile;
import flab.project.sharemyhobby.model.user.User;
import flab.project.sharemyhobby.util.FileUploader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.*;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileMapper profileMapper;

    @Mock
    private FileUploader fileUploader;

    @InjectMocks
    private ProfileService profileService;

    private MultipartFile profileImage;

    User user;

    private MultipartFile getImageFile(String fileName) throws IOException {
        URL testImageUrl = getClass().getResource(fileName);
        File testImage = new File(testImageUrl.getFile());
        return new MockMultipartFile(fileName, new FileInputStream(testImage));
    }

    @BeforeEach
    void setUp() throws IOException {
        profileImage = getImageFile("/test_profile.jpg");
    }

    @Test
    @DisplayName("프로필을 등록하면 DB에 저장된 프로필 정보를 리턴한다")
    void testRegisterProfileAndReturnProfileInfo() throws IOException {
        String statusMessage = "반갑습니다!";
        Profile profile = profileService.registerProfile(1L, profileImage, statusMessage);

        assertThat(profile.getId()).isNotNull();
        assertThat(profile.getUserId()).isEqualTo(1L);
        assertThat(profile.getStatusMessage()).isEqualTo(statusMessage);
    }

    @Test
    @DisplayName("프로필을 등록을 실패하면 FileUploadException 발생시킨다")
    void testThrowFileUploadExceptionIfProfileRegisterFail() throws IOException {
        doThrow(FileUploadException.class)
                .when(fileUploader)
                .upload(profileImage);

        Throwable thrown = catchThrowable(() -> {
            profileService.registerProfile(1L, profileImage, "아 배고파");
        });

        verify(fileUploader).upload(profileImage);
        assertThat(thrown).isInstanceOf(FileUploadException.class);
    }

}