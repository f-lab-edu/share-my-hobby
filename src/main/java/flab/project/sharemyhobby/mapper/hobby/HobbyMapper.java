package flab.project.sharemyhobby.mapper.hobby;

import flab.project.sharemyhobby.model.hobby.HobbyInfo;
import flab.project.sharemyhobby.model.hobby.LikeHobby;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HobbyMapper {

    List<HobbyInfo> findAllHobbyInfo();

    void saveLikeHobby(@Param("likeHobbyList") List<LikeHobby> likeHobbyList, @Param("userId") Long userId);

    List<HobbyInfo> findHobbyInfoById(List<LikeHobby> likeHobbyList);

}
