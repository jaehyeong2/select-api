package jjfactory.jpabasic.config;

import com.google.gson.Gson;
import jjfactory.jpabasic.dto.ErrorResponse;
import jjfactory.jpabasic.error.ErrorCode;
import jjfactory.jpabasic.handler.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;




    private void invalidTokenException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        log.info("invalidTokenException");

        PrintWriter out = httpServletResponse.getWriter();
        httpServletResponse.setContentType("application/json");
        httpServletRequest.setCharacterEncoding("UTF-8");
        final ErrorResponse exception = ErrorResponse.of(ErrorCode.HANDLE_INVALID_TOKEN);
        out.print(new Gson().toJson(exception));
        out.flush();
    }

}
