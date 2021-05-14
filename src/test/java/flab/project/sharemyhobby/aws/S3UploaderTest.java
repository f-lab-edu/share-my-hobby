package flab.project.sharemyhobby.aws;

import flab.project.sharemyhobby.util.FileUploader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class S3UploaderTest {

    private FileUploader fileUploader;

    private MultipartFile profileImage;

    private MultipartFile getTestImageFile() {
        String fileName = "/test_profile.jpg";
        URL testProfileUrl = getClass().getResource(fileName);
        assertThat(testProfileUrl).isNotNull();

        MultipartFile testProfileImage = null;
        try {
            File file = new File(testProfileUrl.getFile());
            testProfileImage = new MockMultipartFile(fileName, "test_profile.jpg", "image/jpg", new FileInputStream(file));
        } catch (IOException e) {
            log.error("Failed to read test image : {}", e.getMessage());
        }
        return testProfileImage;
    }

    @BeforeAll
    void setUp() {
        String region = "";
        String accessKey = "";
        String secretKey = "";
        String bucketName = "";

        AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        S3Client s3Client = S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                    .build();

        fileUploader = new S3Uploader(s3Client, bucketName);
        profileImage = getTestImageFile();
    }

    @Test
    @DisplayName("AWS S3 버킷으로 프로필 이미지를 업로드를 성공하면 이미지 URL을 리턴한다")
    void testUploadProfileImageToS3AndReturnImageUrl() throws IOException {
        String url = fileUploader.upload(profileImage);

        assertThat(fileUploader.checkExist(profileImage.getOriginalFilename())).isTrue();
        assertThat(url).isNotNull();
        log.info("S3 bucket url: {}", url);
    }

    @Test
    @DisplayName("AWS S3에서 파일 삭제가 성공하면 파일 존재 확인 시 false를 리턴한다")
    void testReturnFalseWhenCheckExistAfterDeleteFile() {
        String originalFilename = profileImage.getOriginalFilename();
        fileUploader.delete(originalFilename);

        assertThat(fileUploader.checkExist(originalFilename)).isFalse();
    }

}