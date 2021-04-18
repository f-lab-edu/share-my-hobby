package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.exception.NotFoundException;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import flab.project.sharemyhobby.util.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Service
@RequiredArgsConstructor
public class BasicLoginService implements LoginService {

    private final UserService userService;

    @Transactional
    public User login(Email email, String password) {
        checkArgument(isNotEmpty(password), "Password must be provided");
        User user = userService.findByEmailAndPassword(email, EncryptionUtils.encryptSHA256(password))
                .orElseThrow(NotFoundException::new);
        user.loginPostProcess();
        userService.updateUser(user);
        return user;
    }

}
