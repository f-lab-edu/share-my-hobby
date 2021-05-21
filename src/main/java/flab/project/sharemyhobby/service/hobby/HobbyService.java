package flab.project.sharemyhobby.service.hobby;

import flab.project.sharemyhobby.mapper.hobby.HobbyMapper;
import flab.project.sharemyhobby.model.hobby.HobbyInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HobbyService {

    private final HobbyMapper hobbyMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "hobbyInfo", key = "#root.method.name", cacheManager = "cacheManager")
    public List<HobbyInfo> findAllHobbyInfo() {
        return hobbyMapper.findAllHobbyInfo();
    }

}
