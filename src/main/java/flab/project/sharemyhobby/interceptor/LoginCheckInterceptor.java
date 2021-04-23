package flab.project.sharemyhobby.interceptor;

import flab.project.sharemyhobby.exception.LoginRequiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static flab.project.sharemyhobby.model.commons.SessionKey.USER_SESSION_KEY;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(USER_SESSION_KEY) == null)
            throw new LoginRequiredException();
        return true;
    }
}
