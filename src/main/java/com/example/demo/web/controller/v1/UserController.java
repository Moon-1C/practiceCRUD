package com.example.demo.web.controller.v1;

import com.example.demo.domain.usecase.record.RecordService;
import com.example.demo.domain.usecase.user.UserService;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.response.TokenResponse;
import com.example.demo.web.dto.request.RecordRequestSave;
import com.example.demo.web.dto.request.RecordRequestUpdate;
import com.example.demo.web.dto.request.UserRequest;
import com.example.demo.web.dto.response.RecordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RecordService recordService;


    @PostMapping("/users")
    public ApiResponse<ApiResponse.SuccessBody<TokenResponse>> users(@RequestBody UserRequest userRequest) {


        return ApiResponseGenerator.success((TokenResponse) null, HttpStatus.OK);

    }





}
