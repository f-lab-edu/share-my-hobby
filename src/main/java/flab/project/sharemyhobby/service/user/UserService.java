package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.mapper.user.UserMapper;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.Status;
import flab.project.sharemyhobby.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Transactional
    public User join(Email email, String nickname, String password) {
        checkArgument(isNotEmpty(nickname), "Nickname must be provided");
        checkArgument(isNotEmpty(password), "Password must be provided");
        checkArgument(password.length() >= 8 && password.length() <= 15,
                "Password must be between 8 and 15 characters.");

        User user = User.builder()
                .id(null).email(email).nickname(nickname)
                .password(passwordEncoder.encode(password))
                .status(Status.DEFAULT)
                .lastLoginAt(now()).createAt(now()).updateAt(now())
                .build();

        long userId = userMapper.saveUser(user);
        return user.toBuilder()
                .id(userId)
                .build();
    }

}