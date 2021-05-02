package flab.project.sharemyhobby.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploader {

    String upload(MultipartFile multipartFile) throws IOException;

}
