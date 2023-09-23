package com.example.demo.web.controller.v1;

import com.example.demo.domain.usecase.user.UserService;
import com.example.demo.repository.entity.UserEntity;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.request.RecordRequest;
import com.example.demo.web.dto.request.UserRequest;
import com.example.demo.web.dto.response.FooResponse;
import com.example.demo.web.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CurdController {

    private final UserService userService;

    @PostMapping("/users")
    public ApiResponse<ApiResponse.SuccessBody<UserResponse>> users(@RequestBody UserRequest userRequest) {
        UserEntity user = userService.join(userRequest);
        UserResponse response = new UserResponse(user.getId());


        return ApiResponseGenerator.success(response, HttpStatus.OK);

    }

//    @PostMapping("/record")
//    public ApiResponse<ApiResponse.SuccessBody<UserResponse>> record(@RequestBody RecordRequest recordRequest) {
//        UserEntity user = userService.join(userRequest);
//        UserResponse response = new UserResponse(user.getId());
//
//
//        return ApiResponseGenerator.success(response, HttpStatus.OK);
//
//    }



}
