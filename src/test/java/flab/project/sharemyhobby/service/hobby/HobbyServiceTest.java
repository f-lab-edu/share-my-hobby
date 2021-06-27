package flab.project.sharemyhobby.service.hobby;

import flab.project.sharemyhobby.exception.hobby.DuplicateLikeHobbyException;
import flab.project.sharemyhobby.mapper.hobby.HobbyMapper;
import flab.project.sharemyhobby.model.api.request.hobby.LikeHobbyRequest;
import flab.project.sharemyhobby.model.hobby.Hobby;
import flab.project.sharemyhobby.model.hobby.HobbyInfo;
import flab.project.sharemyhobby.model.hobby.LikeHobby;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@Slf4j
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class HobbyServiceTest {

    @Mock
    private HobbyMapper hobbyMapper;

    @InjectMocks
    private HobbyService hobbyService;

    List<HobbyInfo> testHobbyInfos = new ArrayList<>();

    @BeforeEach
    void setUp(){
        Hobby hobby1 = new Hobby(1, "축구");
        Hobby hobby2 = new Hobby(2, "야구");
        Hobby hobby3 = new Hobby(3, "헬스");
        Hobby hobby4 = new Hobby(4, "요가");

        List<Hobby> hobbyList1 = new ArrayList<>(Arrays.asList(hobby1, hobby2));
        List<Hobby> hobbyList2 = new ArrayList<>(Arrays.asList(hobby3, hobby4));

        HobbyInfo hobbyInfo1 = new HobbyInfo(1, "액티비티", hobbyList1);
        HobbyInfo hobbyInfo2 = new HobbyInfo(2, "뷰티/건강", hobbyList2);

        testHobbyInfos.add(hobbyInfo1);
        testHobbyInfos.add(hobbyInfo2);
    }

    @Test
    @DisplayName("취미 정보 조회 요청 시 모든 취미 정보 리스트를 리턴한다")
    void testReturnAllHobbyInfoListWhenFindAllHobbyInfo() {
        when(hobbyMapper.findAllHobbyInfo())
                .thenReturn(testHobbyInfos);
        List<HobbyInfo> hobbyInfos = hobbyService.findAllHobbyInfo();

        assertThat(hobbyInfos).isNotNull();
        assertThat(hobbyInfos).hasSize(testHobbyInfos.size());
        assertThat(hobbyInfos).extracting("categoryName").contains("액티비티", "뷰티/건강");
        assertThat(hobbyInfos).filteredOn("categoryName", "액티비티")
                .flatExtracting("hobbies")
                .extracting("hobbyName")
                .contains("축구", "야구");
        assertThat(hobbyInfos).filteredOn("categoryName", "뷰티/건강")
                .flatExtracting("hobbies")
                .extracting("hobbyName")
                .contains("헬스", "요가");
    }

    @Test
    @DisplayName("유저가 좋아하는 취미 등록하면 DB에 저장한 취미 정보 리스트를 리턴한다")
    void testReturnLikeHobbyInfoListWhenRegisterLikeHobby() {
        List<LikeHobby> likeHobbyList = Arrays.asList(
                                            new LikeHobby(2, 1),  // 야구 (액티비티)
                                            new LikeHobby(3, 2)   // 헬스 (뷰티/건강)
                                        );
        LikeHobbyRequest likeHobbyRequest  = new LikeHobbyRequest(likeHobbyList);
        List<HobbyInfo> likeHobbyInfos = Arrays.asList(
                                            new HobbyInfo(1, "액티비티", Arrays.asList(new Hobby(2, "야구"))),
                                            new HobbyInfo(2, "뷰티/건강", Arrays.asList(new Hobby(3, "헬스")))
                                        );

        doNothing().when(hobbyMapper).saveLikeHobby(likeHobbyList, 1L);
        doReturn(likeHobbyInfos).when(hobbyMapper).findHobbyInfoById(likeHobbyList);

        List<HobbyInfo> savedLikeHobbyList = hobbyService.registerLikeHobby(likeHobbyRequest, 1L);

        verify(hobbyMapper, times(1)).saveLikeHobby(likeHobbyList, 1L);
        assertThat(savedLikeHobbyList).extracting("categoryName").contains("액티비티", "뷰티/건강");
        assertThat(savedLikeHobbyList).filteredOn("categoryName", "액티비티")
                .flatExtracting("hobbies")
                .extracting("hobbyName")
                .contains("야구");
        assertThat(savedLikeHobbyList).filteredOn("categoryName", "뷰티/건강")
                .flatExtracting("hobbies")
                .extracting("hobbyName")
                .contains("헬스");
        log.info("유저가 좋아하는 취미정보 : {}", savedLikeHobbyList);
    }

    @Test
    @DisplayName("유저가 좋아하는 취미가 중복으로 입력되면 DuplicateLikeHobbyException을 던진다")
    void testThrowDuplicateLikeHobbyExceptionWhenLikeHobbyAlreadyExists() {
        LikeHobby likeHobby = mock(LikeHobby.class);
        List<LikeHobby> likeHobbyList = Arrays.asList(likeHobby);
        doThrow(DuplicateLikeHobbyException.class)
                .when(hobbyMapper)
                .saveLikeHobby(likeHobbyList, 1L);

        assertThatThrownBy(() -> hobbyMapper.saveLikeHobby(likeHobbyList, 1L))
                .isExactlyInstanceOf(DuplicateLikeHobbyException.class);
    }

}