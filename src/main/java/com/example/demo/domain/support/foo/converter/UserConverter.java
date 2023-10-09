package com.example.demo.domain.support.foo.converter;

import com.example.demo.domain.model.User;
import com.example.demo.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public User from(UserEntity source) {
        return User.builder()
                .id(source.getId())
                .password(source.getPassword())
                .build();
    }

}
