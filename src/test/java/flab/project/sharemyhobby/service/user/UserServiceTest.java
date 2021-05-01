package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.exception.NotFoundException;
import flab.project.sharemyhobby.mapper.user.UserMapper;
import flab.project.sharemyhobby.model.api.request.user.PasswordRequest;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import flab.project.sharemyhobby.util.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

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
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserMapper userMapper;

    private Email email;
    private String nickname;
    private String password;

    private PasswordRequest passwordRequest;


    @BeforeAll
    void setUp() {
        email = new Email("adorno10@naver.com");
        nickname = "cold-pumpkin";
        password = "12345678";

        userService.join(email, nickname, password);
        passwordRequest = new PasswordRequest(password, "87654321");
    }

    @Test
    @DisplayName("회원가입에 성공하면 DB에 저장된 유저의 정보를 리턴한다")
    void testJoinNewUserAndReturnUserInfo() {
        User user = userService.join(new Email("test@gmail.com"), "test", "11111111");
        assertThat(user, is(notNullValue()));
        assertThat(user.getId(), is(notNullValue()));
        log.info("회원 가입 : {}", user);
    }

    @Test
    @DisplayName("이메일로 유저 정보를 찾으면 유저의 정보를 리턴한다")
    void testFindUserByEmailAndReturnUserInfo() {
        User user = userService.findByEmailAndPassword(email, EncryptionUtils.encryptSHA256(password)).orElse(null);
        assertThat(user, is(notNullValue()));
        assertThat(user.getEmail().getAddress(), is(email.getAddress()));
        log.info("이메일 {}: {}", user.getEmail().getAddress(), user);
    }

    @Test
    @DisplayName("유저 정보가 존재하지 않으면 NotFoundException 예외를 발생시킨다")
    void testThrowNotFoundExceptionIfEmailNotExists() {
        Exception exception = assertThrows(NotFoundException.class, ()
                -> loginService.login(new Email("test-invalid@gmail.com"), password));
        assertThat(exception.getMessage(), is("Email address not found"));
    }

    @Test
    @DisplayName("기존 패스워드와 새로운 비밀번호를 받아 패스워드를 변경한다")
    void testUpdatePasswordIfOldPwAndNewPwAreEntered() {
        String oldPw = password;
        String newPw = passwordRequest.getNewPassword();

        userService.updatePassword(1L, passwordRequest);
        User newPwUser = userMapper.findByUserIdAndPassword(1L, EncryptionUtils.encryptSHA256(passwordRequest.getNewPassword()))
                .orElse(null);

        assertThat(oldPw, is(passwordRequest.getOldPassword()));
        assertThat(newPwUser, is(notNullValue()));
        assertThat(EncryptionUtils.encryptSHA256(newPw), is(newPwUser.getPassword()));

        log.info("기존 PW : {}", oldPw);
        log.info("새로운 PW : {}", newPw);
    }

}