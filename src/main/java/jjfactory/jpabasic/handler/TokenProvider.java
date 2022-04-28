package jjfactory.jpabasic.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    private static final long TOKEN_TIME = 1000L * 60 * 24 * 365;
    private static final long REFRESH_TOKEN_VALID_TIME =   1000L * 60 * 24 * 700;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected  void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }




}
