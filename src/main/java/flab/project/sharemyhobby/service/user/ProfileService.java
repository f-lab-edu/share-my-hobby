package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.mapper.user.ProfileMapper;
import flab.project.sharemyhobby.model.user.Profile;
import flab.project.sharemyhobby.util.ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ImageUploader imageUploader;

    private final ProfileMapper profileMapper;

    @Transactional
    public Profile registerProfile(Long userId, MultipartFile profileImage, String statusMessage) {

        String profileImageUrl = uploadProfileImage(profileImage);
        Profile profile = Profile.builder()
                    .id(null)
                    .userId(userId)
                    .profileImageUrl(profileImageUrl)
                    .statusMessage(statusMessage)
                    .build();

        long profileId = profileMapper.saveProfile(profile);
        return profile.toBuilder()
                .id(profileId)
                .build();
    }

    private String uploadProfileImage(MultipartFile profileImage) {
        if (profileImage == null)
            return null;
        String profileImageUrl = null;
        try {
            profileImageUrl = imageUploader.upload(profileImage);
        } catch (S3Exception | IOException e) {
            log.error("Image upload failed : {}", e.getMessage());
        }
        return profileImageUrl;
    }

}