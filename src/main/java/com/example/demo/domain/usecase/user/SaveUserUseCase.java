package com.example.demo.domain.usecase.user;


import com.example.demo.domain.dto.SavedUserInfo;
import com.example.demo.domain.model.User;
import com.example.demo.domain.support.foo.converter.UserConverter;
import com.example.demo.exception.NotMatchPasswordException;
import com.example.demo.repository.entity.UserEntity;
import com.example.demo.repository.repository.UserRepository;
import com.example.demo.security.authentication.authority.Roles;
import com.example.demo.support.token.TokenGenerator;
import com.example.demo.web.dto.request.SaveUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SaveUserUseCase {

    private final UserRepository userRepository;

    private final TokenGenerator tokenGenerator;
    private final UserConverter userConverter;

    private static final Long NOT_EXIST_MEMBER = -1L;

    @Transactional
    public SavedUserInfo execute(SaveUserRequest request) {

        Long existMember = isExistMember(request.getName());

        if (!Objects.equals(existMember, NOT_EXIST_MEMBER)) {
            User user = getMember(existMember);

            validatePassword(user, request.getPassword());

            return getSavedMember(user.getId());
        }

        Long savedMemberId = saveMember(request);

        return getSavedMember(savedMemberId);
    }

    private Long isExistMember(String name) {
        Optional<UserEntity> source = userRepository.findByNameAndDeletedFalse(name);

        if (source.isPresent()) {
            return source.get().getId();
        }

        return NOT_EXIST_MEMBER;
    }

    private User getMember(Long existMember) {
        return userConverter.from(
                Objects.requireNonNull(userRepository.findByIdAndDeletedFalse(existMember).orElse(null)));
    }

    private void validatePassword(User member, String requestPassword) {
        if (!member.isPassword(requestPassword)) {
            throw new NotMatchPasswordException();
        }
    }

    private Long saveMember(SaveUserRequest request) {
        return userRepository
                .save(
                        UserEntity.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(request.getPassword())
                                .build())
                .getId();
    }

    private SavedUserInfo getSavedMember(Long memberId) {
        return SavedUserInfo.builder()
                .id(memberId)
                .token(tokenGenerator.generateAuthToken(memberId, List.of(Roles.USER)))
                .build();
    }



}
