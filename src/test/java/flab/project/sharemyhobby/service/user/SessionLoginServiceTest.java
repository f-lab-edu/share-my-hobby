package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class SessionLoginServiceTest {

    @Autowired
    private SessionLoginService sessionLoginService;

    @Autowired
    private UserService userService;

    private Email email;
    private String nickname;
    private String password;


    @BeforeAll
    void setUp() {
        email = new Email("test@naver.com");
        nickname = "test-nickname";
        password = "12345678";

        userService.join(email, nickname, password);
    }

    @Test
    @Order(1)
    @DisplayName("로그인 성공하면 해당 유저 정보를 리턴한다")
    void testLoginAndReturnUserInfo() {
        User user = sessionLoginService.login(email, password);

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isNotNull();
        assertThat(user.getId()).isNotNull();
        log.info("로그인 유저 : {}", user);
    }

}