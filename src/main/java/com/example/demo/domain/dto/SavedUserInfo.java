package com.example.demo.domain.dto;

import com.example.demo.support.token.AuthToken;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SavedUserInfo {


    private Long id;
    private AuthToken token;

}
