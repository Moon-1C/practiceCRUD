package com.example.demo.web.dto.response;

import lombok.Data;

import java.util.HashMap;


@Data
public class TokenResponse {

    private Long Id;

    private HashMap<String,String> authToken;



}
