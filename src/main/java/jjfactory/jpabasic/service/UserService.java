package jjfactory.jpabasic.service;

import jjfactory.jpabasic.domain.user.User;
import jjfactory.jpabasic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void save(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findOne(id);
    }

    public List<User> getUserByName(String name){
        return userRepository.findByName(name);
    }

    public List<User> getUserList(){
        return userRepository.findAll();
    }

    private void validateDuplicateUser(User user) {
        List<User> users = userRepository.findByName(user.getName());
        if(!users.isEmpty()){
            throw new IllegalStateException("중복된 회원입니다");
        }
    }
}
