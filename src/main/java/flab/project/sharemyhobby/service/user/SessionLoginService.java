package flab.project.sharemyhobby.service.user;

import flab.project.sharemyhobby.exception.AlreadyLoginException;
import flab.project.sharemyhobby.exception.NotFoundException;
import static flab.project.sharemyhobby.model.commons.SessionKey.USER_SESSION_KEY;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.User;
import flab.project.sharemyhobby.util.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import java.rmi.AlreadyBoundException;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final UserService userService;

    private final HttpSession httpSession;

    @Override
    public User login(Email email, String password) {
        checkArgument(isNotEmpty(password), "Password must be provided");
        if (httpSession.getAttribute(USER_SESSION_KEY) != null) {
            throw new AlreadyLoginException();
        }

        User user = userService.findByEmailAndPassword(email, EncryptionUtils.encryptSHA256(password))
                .orElseThrow(NotFoundException::new);
        user.loginPostProcess();
        userService.updateUser(user);
        httpSession.setAttribute(USER_SESSION_KEY, user.getId());
        return user;
    }
}
