package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.mapper.user.UserMapper;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.Status;
import flab.project.sharemyhobby.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserMapper userMapper;

    @Transactional
    public User join(Email email, String nickname, String password) {
        // 암호화 일단 skip
        // checkNull - Guava
        User user = User.builder()
                .id(null).email(email).nickname(nickname).password(password)
                .status(Status.DEFAULT).lastLoginAt(now()).createAt(now()).updateAt(now())
                .build();

        long userId = userMapper.saveUser(user);
        return user.toBuilder()
                .id(userId)
                .build();
    }

}