package com.example.demo.web.controller.v1;

import com.example.demo.domain.dto.SavedUserInfo;
import com.example.demo.domain.usecase.record.RecordService;
import com.example.demo.domain.usecase.user.SaveUserUseCase;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.response.TokenResponse;
import com.example.demo.web.dto.request.SaveUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final SaveUserUseCase saveUserUseCase;

    private final RecordService recordService;


    @PostMapping
    public ApiResponse<ApiResponse.SuccessBody<SavedUserInfo>> save(
            @RequestBody @Valid SaveUserRequest request) {
        SavedUserInfo res = saveUserUseCase.execute(request);
        return ApiResponseGenerator.success(res, HttpStatus.CREATED);
    }


}
