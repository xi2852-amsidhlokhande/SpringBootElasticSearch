package com.amsidh.mvc.controller;

import com.amsidh.mvc.entity.Article;
import com.amsidh.mvc.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleRepository articleRepository;

    @PostMapping
    public Article saveArticle(@RequestBody Article article) {
        return this.articleRepository.save(article);
    }

    @GetMapping("/{authorName}")
    public Page<Article> getArticleByAuthorName(@PathVariable(name = "authorName", required = true) String authorName) {
        Page<Article> authorByName = articleRepository.findByAuthorsName(authorName, PageRequest.of(0, 10));
        return authorByName;
    }

    @GetMapping("/custom/{authorName}")
    public Page<Article> getByAuthorsNameUsingCustomQuery(@PathVariable(name = "authorName", required = true) String authorName) {
        Page<Article> authorByNameCustomQuery = articleRepository.findByAuthorsNameUsingCustomQuery(authorName, PageRequest.of(0, 10));
        return authorByNameCustomQuery;
    }

    @GetMapping("/tags/{tag}")
    public Page<Article> getByFilteredTagQuery(@PathVariable(name = "tag", required = true) String tag) {
        Page<Article> authorByTag = articleRepository.findByFilteredTagQuery(tag, PageRequest.of(0, 10));
        return authorByTag;
    }

    @GetMapping("/{authorName}/{tag}")
    public Page<Article> findByAuthorsNameAndFilteredTagQuery(@PathVariable(name = "authorName", required = true) String authorName, @PathVariable(name = "tag", required = true) String tag) {
        Page<Article> authorByNameAndTag = articleRepository.findByAuthorsNameAndFilteredTagQuery(authorName, tag, PageRequest.of(0, 10));
        return authorByNameAndTag;
    }

    @GetMapping("/query/{authorName}")
    public Iterable<Article> getAuthorByQueryBuilder(@PathVariable(name = "authorName", required = true) String authorName) {
        BoolQueryBuilder queryBuilder = QueryBuilders
                .boolQuery();
        queryBuilder.must(QueryBuilders.matchQuery("authors.name", authorName));
        Iterable<Article> articles = articleRepository.search(queryBuilder);
        return articles;
    }

    @GetMapping("/query/{authorName}/{tag}")
    public List<Article> getAuthorByQueryBuilder(@PathVariable(name = "authorName", required = true) String authorName, @PathVariable(name = "tag", required = true) String tag) {
        BoolQueryBuilder queryBuilder = QueryBuilders
                .boolQuery();
        queryBuilder.must(QueryBuilders.matchQuery("authors.name", authorName));
        queryBuilder.must(QueryBuilders.termsQuery("tags", tag));

        Iterable<Article> articles = articleRepository.search(queryBuilder);
        return stream(articles.spliterator(), true).collect(toList());
    }

}
