package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.mapper.user.UserMapper;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private Email email;
    private String name;
    private String password;

    @BeforeAll
    void setUp() {
        email = new Email("test@gmail.com");
        name = "박찬호";
        password = "1234";
    }

    @Test
    @Order(1)
    void 새로운_사용자를_추가한다() {
        User user = userService.join(email, name, password);
        assertThat(user, is(notNullValue()));
        assertThat(user.getId(), is(notNullValue()));
        assertThat(user.getEmail(), is(email));
        log.info("Inserted user: {}", user);
    }
}