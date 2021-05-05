package flab.project.sharemyhobby.aws;

import flab.project.sharemyhobby.util.FileUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;

@Slf4j
@RequiredArgsConstructor
public final class S3Uploader implements FileUploader {

    private final S3Client s3Client;

    private final String bucketName;

    private final static String S3_DIR_NAME = "profile";

    @Override
    public String upload(MultipartFile uploadImage) throws IOException {
        String filePath = S3_DIR_NAME + uploadImage.getName();
        return putS3(uploadImage, filePath);
    }

    private String putS3(MultipartFile uploadImage, String key) throws IOException {
        PutObjectRequest objectRequest = getPutObjectRequest(key);
        RequestBody rb = RequestBody.fromInputStream(uploadImage.getInputStream(), uploadImage.getSize());
        s3Client.putObject(objectRequest, rb);
        return getUploadImageUrl(key);
    }

    private PutObjectRequest getPutObjectRequest(String key) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
    }

    private String getUploadImageUrl(String key) {
        S3Utilities s3Utilities = s3Client.utilities();
        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        URL url = s3Utilities.getUrl(request);
        return url.toString();
    }

}
