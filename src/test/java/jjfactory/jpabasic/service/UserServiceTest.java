package jjfactory.jpabasic.service;

import jjfactory.jpabasic.domain.user.Address;
import jjfactory.jpabasic.domain.user.User;
import jjfactory.jpabasic.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Rollback(value = true)
    @Test
    @DisplayName("회원가입 테스트")
    void join(){

        User user = new User("kim","wogud22@naver.com",new Address("BUSAN","456","1234"));

        Long findId = userService.save(user);
        Assertions.assertThat(findId).isEqualTo(user.getId());
    }

}