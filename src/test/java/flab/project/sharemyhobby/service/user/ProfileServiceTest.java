package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.exception.user.DuplicateProfileException;
import flab.project.sharemyhobby.exception.user.FileUploadException;
import flab.project.sharemyhobby.exception.user.ProfileNotFoundException;
import flab.project.sharemyhobby.mapper.user.ProfileMapper;
import flab.project.sharemyhobby.model.user.Profile;
import flab.project.sharemyhobby.util.FileUploader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    private MultipartFile newProfileImage;

    private MultipartFile getImageFile(String originalFileName) throws IOException {
        String fileName = "/" + originalFileName;
        URL testImageUrl = getClass().getResource(fileName);
        assertThat(testImageUrl).isNotNull();

        File testImage = new File(testImageUrl.getFile());
        return new MockMultipartFile(fileName, originalFileName, "image/jpg", new FileInputStream(testImage));
    }

    @BeforeEach
    void setUp() throws IOException {
        profileImage = getImageFile("test_profile.jpg");
        newProfileImage = getImageFile("test_shiba.jpg");
    }

    @Test
    @DisplayName("프로필을 등록하면 DB에 저장된 프로필 정보를 리턴한다")
    void testRegisterProfileAndReturnProfileInfo() {
        String statusMessage = "반갑습니다!";
        Profile profile = profileService.registerProfile(1L, profileImage, statusMessage);
        assertThat(profile.getId()).isNotNull();
        assertThat(profile.getUserId()).isEqualTo(1L);
        assertThat(profile.getStatusMessage()).isEqualTo(statusMessage);
    }

    @Test
    @DisplayName("AWS S3에 이미지 파일 업로드 실패 시 FileUploadException 발생시킨다")
    void testThrowFileUploadExceptionIfS3ImageUploadFail() throws IOException {
        doThrow(S3Exception.class)
                .when(fileUploader)
                .upload(profileImage);

        Throwable thrown = catchThrowable(() -> {
            profileService.registerProfile(1L, profileImage, "아 배고파");
        });

        verify(fileUploader).upload(profileImage);
        assertThat(thrown).isInstanceOf(FileUploadException.class);
    }

    @Test
    @DisplayName("이미지 파일 읽기 실패 시 FileUploadException 발생시킨다")
    void testThrowFileUploadExceptionIfImageReadFail() throws IOException {
        doThrow(IOException.class)
                .when(fileUploader)
                .upload(profileImage);

        Throwable thrown = catchThrowable(() -> {
            profileService.registerProfile(1L, profileImage, "아 배고파");
        });

        verify(fileUploader).upload(profileImage);
        assertThat(thrown).isInstanceOf(FileUploadException.class);
    }

    @Test
    @DisplayName("프로필 업데이트 시 새로운 이미지와 상태메시지로 대체되며 새로운 프로필 정보를 리턴한다")
    void testUpdateStatusMessageAndProfileImageThenReturnNewProfile() {
        Profile oldProfile = new Profile(1L, 1L, "/test_profile.jpg", "안녕하세요");
        when(profileMapper.findByUserId(1L))
                .thenReturn(Optional.of(oldProfile));

        String newStatusMessage = "새로운 상태 메시지";
        Profile newProfile = profileService.updateProfile(1L, newProfileImage, newStatusMessage);

        assertThat(newProfile.getId()).isNotNull();
        assertThat(newProfile.getUserId()).isEqualTo(1L);
        assertThat(newProfile.getStatusMessage()).isEqualTo(newStatusMessage);
    }

    @Test
    @DisplayName("프로필 수정 시 유저 프로필 정보가 존재하지 않으면 ProfileNotFoundException을 던진다")
    void testThrowProfileNotFoundExceptionIfProfileNotExists() {
        assertThatThrownBy(() -> profileService.updateProfile(100L, profileImage, "새로운 상태 메시지"))
                .isExactlyInstanceOf(ProfileNotFoundException.class);
    }

    @Test
    @DisplayName("프로필 등록 시 이미 해당 유저의 프로필이 존재하면 DuplicateProfileException 발생시킨다")
    void testThrowDuplicateProfileExceptionIfProfileAlreadyExists() {
        doThrow(DuplicateKeyException.class)
                .when(profileMapper)
                .saveProfile(any(Profile.class));

        assertThatThrownBy(() -> profileService.registerProfile(2L, profileImage, "안녕하세요!"))
                .isInstanceOf(DuplicateProfileException.class);
    }

}