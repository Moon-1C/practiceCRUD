package com.example.demo.web.controller.v1;

import com.example.demo.domain.dto.response.FooDomainResponse;
import com.example.demo.domain.usecase.foo.FooService;
import com.example.demo.support.ApiResponse;
import com.example.demo.support.ApiResponseGenerator;
import com.example.demo.web.dto.request.FooRequest;
import com.example.demo.web.dto.response.FooResponse;
import com.example.demo.web.support.converter.FooControllerConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/foo")
@RequiredArgsConstructor
public class FooController {

	private final FooService fooService;
	private final FooControllerConverter fooControllerConverter;

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<FooResponse>> foo(@RequestBody FooRequest request) {
		FooDomainResponse response = fooService.execute(fooControllerConverter.from(request));
		return ApiResponseGenerator.success(fooControllerConverter.to(response), HttpStatus.OK);
	}
}
