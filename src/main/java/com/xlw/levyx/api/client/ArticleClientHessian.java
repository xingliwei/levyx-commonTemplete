package com.xlw.levyx.api.client;


import com.xlw.levyx.model.Article;

import java.util.List;

/**
 * Created by levyx on 2017/7/25.
 */
public interface ArticleClientHessian {

    String hello(String msg);

    void save(Article article);

    Article getArticle(String id);

    List<Article> getArticleList(Article article,String commentContent,Integer limit,Integer offset);

}
