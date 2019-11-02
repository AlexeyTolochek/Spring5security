package ru.java.mentor.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.java.mentor.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        HttpSession session = request.getSession();
        User user = (User) authentication.getPrincipal();

        session.setAttribute("user", user);
        session.setAttribute("authorities", authentication.getAuthorities());

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("user");
    }
}
