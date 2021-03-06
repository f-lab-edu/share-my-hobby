package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.exception.user.DuplicateProfileException;
import flab.project.sharemyhobby.exception.user.FileUploadException;
import flab.project.sharemyhobby.exception.user.ProfileNotFoundException;
import flab.project.sharemyhobby.mapper.user.ProfileMapper;
import flab.project.sharemyhobby.model.user.Profile;
import flab.project.sharemyhobby.util.FileUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final FileUploader fileUploader;

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

        long profileId = 0;
        try {
            profileId = profileMapper.saveProfile(profile);
        } catch (DuplicateKeyException e) {
            log.error("Profile register failed");
            throw new DuplicateProfileException();
        }

        return profile.toBuilder()
                .id(profileId)
                .build();
    }

    @Transactional
    public Profile updateProfile(Long userId, MultipartFile newProfileImage, String newStatusMessage) {
        Profile oldProfile = profileMapper.findByUserId(userId)
                .orElseThrow(ProfileNotFoundException::new);

        fileUploader.delete(getImageFileNameFromUrl(oldProfile.getProfileImageUrl()));

        String newProfileImageUrl = uploadProfileImage(newProfileImage);
        Profile newProfile = oldProfile.toBuilder()
                .profileImageUrl(newProfileImageUrl)
                .statusMessage(newStatusMessage)
                .build();

        profileMapper.updateProfile(newProfile);
        return newProfile;
    }

    private String uploadProfileImage(MultipartFile profileImage) {
        if (profileImage == null)
            return null;
        String profileImageUrl = null;
        try {
            profileImageUrl = fileUploader.upload(profileImage);
        } catch (S3Exception | IOException e) {
            log.error("Image upload failed : {}", e.getMessage());
            throw new FileUploadException();
        }
        return profileImageUrl;
    }

    private String getImageFileNameFromUrl(String profileImageUrl) {
        return profileImageUrl.substring(profileImageUrl.lastIndexOf("/") + 1);
    }
}
