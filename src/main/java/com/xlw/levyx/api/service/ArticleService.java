package com.xlw.levyx.api.service;

import com.xlw.levyx.api.client.ArticleClient;
import com.xlw.levyx.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by levyx on 2017/7/25.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleClient articleClient;

    public String hello(){
        return articleClient.helloHessian();
    }

    public Article getArticle(String id){
        return articleClient.getArticle(id);
    }

    public void saveArticle(Article article){
        articleClient.saveArticle(article);
    }

    public List<Article> getArticleList(Article article,String commentContent,Integer limit,Integer offset){
        return articleClient.getArticleList(article, commentContent,limit,offset);
    }
}
