package io.youngwon.app.config.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    static String _401 = "{\"success\":false,\"response\":null,\"error\":{\"message\":\"Unauthorized\",\"status\":401}}";

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setHeader("content-type", "application/json");
        httpServletResponse.getWriter().write(_401);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
