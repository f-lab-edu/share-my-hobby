package flab.project.sharemyhobby.mapper.hobby;

import flab.project.sharemyhobby.model.hobby.HobbyInfo;

import java.util.List;

public interface HobbyMapper {

    List<HobbyInfo> findAllHobbyInfo();

}
