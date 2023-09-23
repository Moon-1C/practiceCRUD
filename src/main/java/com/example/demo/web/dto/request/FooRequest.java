package com.example.demo.web.dto.request;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FooRequest {

	private String name;
}
