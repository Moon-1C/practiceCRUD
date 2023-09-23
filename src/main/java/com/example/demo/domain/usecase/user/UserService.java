package com.example.demo.domain.usecase.user;


import com.example.demo.repository.entity.UserEntity;
import com.example.demo.repository.repository.UserRepository;
import com.example.demo.web.dto.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity join(UserRequest userRequest) {
        UserEntity joinUser = new UserEntity();
        joinUser.setName(userRequest.getName());
        joinUser.setAge(userRequest.getAge());
        joinUser.setEmail(userRequest.getEmail());
        joinUser.setPassword(userRequest.getPassword());
        userRepository.save(joinUser);

        return joinUser;

    }




//    public UserEntity login(UserDto userDto) {
//
//
//    }



}
