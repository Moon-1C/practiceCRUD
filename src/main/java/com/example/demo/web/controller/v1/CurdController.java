package com.example.demo.web.controller.v1;

import com.example.demo.domain.usecase.record.RecordService;
import com.example.demo.domain.usecase.user.UserService;
import com.example.demo.repository.entity.TokenInfo;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.response.TokenResponse;
import com.example.demo.web.dto.request.RecordRequestSave;
import com.example.demo.web.dto.request.RecordRequestUpdate;
import com.example.demo.web.dto.request.UserRequest;
import com.example.demo.web.dto.response.RecordResponse;
import com.example.demo.web.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CurdController {

    private final UserService userService;

    private final RecordService recordService;


    @PostMapping("/users")
    public ApiResponse<ApiResponse.SuccessBody<TokenResponse>> users(@RequestBody UserRequest userRequest) {

        Long userId=null;
        try{
             userId = userService.getUserId(userRequest.getName());

        }catch (UsernameNotFoundException e){

            UserResponse userResponse = userService.join(userRequest);
            userId = userResponse.getId();
        }

        TokenInfo token = userService.login(userRequest.getName(), userRequest.getPassword());
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAuthToken(new HashMap<>());
        tokenResponse.getAuthToken().put("refreshToken", token.getRefreshToken());
        tokenResponse.getAuthToken().put("accessToken",token.getAccessToken());
        tokenResponse.setId(userId);

        return ApiResponseGenerator.success(tokenResponse, HttpStatus.OK);

    }

    @PostMapping("/record")
    public ApiResponse<ApiResponse.SuccessBodyWithNoData> record(@RequestBody RecordRequestSave recordRequestSave) {

        recordService.saveRecord(recordRequestSave);

        return ApiResponseGenerator.successWithNoData(HttpStatus.OK);

    }

    @GetMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> showRecord(@RequestParam Long id) {
        RecordResponse findRecord = recordService.getRecordResponseByRid(id);
        return ApiResponseGenerator.success(findRecord, HttpStatus.OK);

    }

    @PutMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<RecordResponse>> updateRecord(@RequestBody RecordRequestUpdate recordRequestUpdate) {

        RecordResponse updateRecord = recordService.update(recordRequestUpdate);



        return ApiResponseGenerator.success(updateRecord, HttpStatus.OK);


    }

    @DeleteMapping("/record")
    public ApiResponse<ApiResponse.SuccessBody<Void>> deleteRecord(@RequestParam Long id) {
        recordService.delete(id);
        return ApiResponseGenerator.success( HttpStatus.OK);

    }



}
