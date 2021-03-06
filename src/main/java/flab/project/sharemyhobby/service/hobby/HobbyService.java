package flab.project.sharemyhobby.service.hobby;

import flab.project.sharemyhobby.exception.hobby.DuplicateLikeHobbyException;
import flab.project.sharemyhobby.mapper.hobby.HobbyMapper;
import flab.project.sharemyhobby.model.api.request.hobby.LikeHobbyRequest;
import flab.project.sharemyhobby.model.hobby.HobbyInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyMapper hobbyMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "hobbyInfo", key = "#root.method.name")
    public List<HobbyInfo> findAllHobbyInfo() {
        return hobbyMapper.findAllHobbyInfo();
    }

    @Transactional
    public List<HobbyInfo> registerLikeHobby(LikeHobbyRequest likeHobbyRequest, Long userId) {
        try {
            hobbyMapper.saveLikeHobby(likeHobbyRequest.getLikeHobbyList(), userId);
        } catch (DuplicateKeyException e) {
            throw new DuplicateLikeHobbyException(likeHobbyRequest, userId);
        }

        return findLikeHobbyInfo(likeHobbyRequest);
    }

    private List<HobbyInfo> findLikeHobbyInfo(LikeHobbyRequest likeHobbyRequest) {
        return hobbyMapper.findHobbyInfoById(likeHobbyRequest.getLikeHobbyList());
    }

}
