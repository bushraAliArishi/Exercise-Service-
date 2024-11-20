package com.example.exerciseservice.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Article {
    @NotEmpty
    private String id;
    @NotEmpty
    @Size(max = 100,message ="Maximum length of 100 characters." )
    private String title;
    @NotEmpty
    @Size(min = 4, max = 20,message ="Minimum length is 4 & Maximum length of 20 characters ." )
    private String author;
    @NotEmpty
    @Size(min = 200,message ="Minimum length is 200 characters." )
    private String content;
    @NotEmpty
    @Pattern(regexp = "^(politics|sports|technology)$")
    private String category;
    @NotEmpty
    private String imageUrl;

    private boolean isPublished=false;
    private LocalDate publishDate;



}
