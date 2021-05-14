package flab.project.sharemyhobby.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploader {

    String upload(MultipartFile multipartFile) throws IOException;

    void delete(String originalFileName);

    boolean checkExist(String originalFileName);

}
