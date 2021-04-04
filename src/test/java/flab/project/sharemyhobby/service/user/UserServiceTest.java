package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/*
 * @SpringBootTest
 *  - 통합 테스트를 제공하는 기본적인 Spring Boot 테스트 애노테이션
 *  - @SpringBootApplication을 찾아 하위 Bean들을 스캔하며 빈으로 등록해 테스트에 필요한 의존성을 추가
 * @ActiveProfiles("test")
 *  - test 프로파일에 해당하는 환경 값이 설정되도록 지정
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS)
 *  - 테스트 인스턴스의 라이프 사이클을 클래스 단위로 지정함으로써 테스트 실행 중 단 하나의 인스턴스를 생성하여 사용
 *  - @BeforeAll을 정적 메소드로 지정하지 않아도 됨
 * @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 *  - 각 테스트 메소드의 실행 순서 지정
 *  - OrderAnnotation인 경우 @Order(순서) 애노테이션을 사용해 순서
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    private Email email;
    private String name;
    private String password;

    @BeforeAll
    void setUp() {
        email = new Email("test@gmail.com");
        name = "박찬호";
        password = "12345678";
    }

    @Test
    @Order(1)
    @DisplayName("회원가입에 성공하면 DB에 저장된 유저의 정보를 리턴한다")
    void testJoinNewUserAndReturnUserInfo() {
        User user = userService.join(email, name, password);
        assertThat(user, is(notNullValue()));
        assertThat(user.getId(), is(notNullValue()));
        assertThat(user.getEmail(), is(email));
        log.info("회원가입 : {}", user);
    }
}