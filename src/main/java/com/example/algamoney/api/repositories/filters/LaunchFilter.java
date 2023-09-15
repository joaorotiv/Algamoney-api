package com.example.algamoney.api.repositories.filters;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class LaunchFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDueDate;

}
