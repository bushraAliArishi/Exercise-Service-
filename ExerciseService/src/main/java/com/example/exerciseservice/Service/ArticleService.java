package com.example.exerciseservice.Service;

import com.example.exerciseservice.Model.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArticleService {
    ArrayList<Article>articles=new ArrayList<>();

    public ArrayList<Article> getArticles(){
        return articles;
    }
    public void addArticle(Article article){
        articles.add(article);
    }
    public boolean updateArticle(String id,Article article){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId().equalsIgnoreCase(id)){
            articles.set(i,article);
            return true;}
        }return false;
    }
    public boolean deleteArticle(String id){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId().equalsIgnoreCase(id)){
                articles.remove(articles.get(i));
                return true;}
        }return false;
    }
    public boolean publishArticle(String id){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId().equalsIgnoreCase(id)){
                articles.get(i).setPublished(true);
                return true;}
        }return false;
    }
    public ArrayList<Article> getPublishArticle(){
        ArrayList<Article>publishedArticle=new ArrayList<>();
        for (Article article : articles) {
            if (article.isPublished()==true){
                publishedArticle.add(article);
            }
        }
        return publishedArticle;
    }
    public ArrayList<Article> getArticleByCategory(String cat){
        ArrayList<Article>categoryArticle=new ArrayList<>();

        for (Article article : articles) {
            if (article.getCategory().equalsIgnoreCase(cat)){
                categoryArticle.add(article);
            }
        }
        return categoryArticle;
    }


}
