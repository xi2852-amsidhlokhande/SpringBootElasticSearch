package com.amsidh.mvc;

import com.amsidh.mvc.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ArticleService {
    Article saveArticle(Article article);
    void deleteArticle(Article article);
    Article findOne(String id);
    Iterable<Article> findAll();
    Page<Article> findByAuthorName(String authorName, PageRequest pageRequest);

}
