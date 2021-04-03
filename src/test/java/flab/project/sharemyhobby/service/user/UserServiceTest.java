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
    void 새로운_사용자를_추가한다() {
        User user = userService.join(email, name, password);
        assertThat(user, is(notNullValue()));
        assertThat(user.getId(), is(notNullValue()));
        assertThat(user.getEmail(), is(email));
        log.info("회원가입 : {}", user);
    }
}