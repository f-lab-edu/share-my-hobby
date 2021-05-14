package flab.project.sharemyhobby.mapper.user;

import flab.project.sharemyhobby.model.user.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProfileMapper {

    long saveProfile(Profile profile);

}
