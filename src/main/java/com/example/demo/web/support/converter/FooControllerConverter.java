package com.example.demo.web.support.converter;

import com.example.demo.domain.dto.request.FooDomainRequest;
import com.example.demo.domain.dto.response.FooDomainResponse;
import com.example.demo.web.dto.request.FooRequest;
import com.example.demo.web.dto.response.FooResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FooControllerConverter {

	public FooDomainRequest from(FooRequest source) {

		return FooDomainRequest.builder().name(source.getName()).build();
	}

	public FooResponse to(FooDomainResponse source) {
		return FooResponse.builder().name(source.getName()).build();
	}
}
