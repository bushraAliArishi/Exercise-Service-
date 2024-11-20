package com.example.exerciseservice.Controller;

import com.example.exerciseservice.ApiResponse.ApiResponse;
import com.example.exerciseservice.Model.Article;
import com.example.exerciseservice.Service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/display")
    public ResponseEntity getArticle() {
        return ResponseEntity.status(200).body(articleService.getArticles());
    }

    @PostMapping("/add")
    public ResponseEntity addArticle(@RequestBody @Valid Article article, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        articleService.addArticle(article);
        return ResponseEntity.status(200).body(new ApiResponse("Article added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArticle(@PathVariable String id, @RequestBody @Valid Article article, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = articleService.updateArticle(id, article);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Article Updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle(@PathVariable String id) {
        boolean isdeleted = articleService.deleteArticle(id);
        if (isdeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Article deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publishArticle(@PathVariable String id) {
        boolean ispublished = articleService.publishArticle(id);
        if (ispublished) {
            return ResponseEntity.status(200).body(new ApiResponse("Article published successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @GetMapping("/display-publish")
    public ResponseEntity getPublishedArticle() {
        if (articleService.getPublishArticle().size() > 0) {
            return ResponseEntity.status(200).body(articleService.getPublishArticle());
        }
        return ResponseEntity.status(200).body(new ApiResponse("there is no published Articles"));

    }

    @GetMapping("/display-bycategory/{category}")
    public ResponseEntity getcategoryArticle(@PathVariable String category) {
        if (category == "politics" || category == "sports" || category == "technology") {

            if (articleService.getArticleByCategory(category).size() > 0) {
                return ResponseEntity.status(200).body(articleService.getArticleByCategory(category));
            }
            return ResponseEntity.status(200).body(new ApiResponse("there is no published Articles in (" + category + ") category "));
        }
        return ResponseEntity.status(400).body(new ApiResponse("the avaleabel  categories are politics,sports,technology "));
    }


}
