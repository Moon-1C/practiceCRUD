package com.example.demo.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecordRequestUpdate {

    private Long id;

    private String title;

    private String content;
}
