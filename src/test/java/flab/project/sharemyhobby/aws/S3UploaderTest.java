package flab.project.sharemyhobby.aws;

//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import flab.project.sharemyhobby.util.ImageUploader;
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

    private ImageUploader imageUploader;

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

        imageUploader = new S3Uploader(s3Client, bucketName);
    }

    @Test
    @DisplayName("AWS S3 버킷으로 프로필 이미지를 업로드를 성공하면 이미지 URL을 리턴한다")
    void testUploadProfileImageToS3AndReturnImageUrl() throws IOException {
        String fileName = "/test_profile.jpg";
        URL testProfile = getClass().getResource(fileName);

        assertThat(testProfile).isNotNull();

        File file = new File(testProfile.getFile());
        FileInputStream input = new FileInputStream(file);
        MultipartFile profileImage = new MockMultipartFile(fileName, input);
        String url = imageUploader.upload(profileImage);

        assertThat(url).isNotNull();
        log.info("S3 bucket url: {}", url);
    }

}