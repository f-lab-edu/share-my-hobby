package flab.project.sharemyhobby.mapper.user;

import flab.project.sharemyhobby.model.user.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface ProfileMapper {

    long saveProfile(Profile profile);

    void updateProfile(Profile profile);

    Optional<Profile> findByUserId(Long userId);

}