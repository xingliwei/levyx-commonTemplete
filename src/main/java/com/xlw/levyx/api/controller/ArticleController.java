package com.xlw.levyx.api.controller;

import com.xlw.levyx.api.service.ArticleService;
import com.xlw.levyx.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by levyx on 2017/7/25.
 */
@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("hessian")
    public void hello(){
        String hello = articleService.hello();
        System.out.println(hello);
    }

    @RequestMapping("getById")
    @ResponseBody
    public Object hello(@RequestParam("id")String id){
        Article article = articleService.getArticle(id);
        return article;
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody Article article){
        articleService.saveArticle(article);
        return new HashMap<>();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Object save(@RequestParam(value = "authorId",required = false)String authorId,
                       @RequestParam(value = "authorName",required = false)String authorName,
                       @RequestParam(value = "content",required = false)String content,
                       @RequestParam(value = "commentContent",required = false)String commentContent,
                       @RequestParam(value = "limit",required = false,defaultValue = "10")Integer limit,
                       @RequestParam(value = "offset",required = false,defaultValue = "0")Integer offset){
        Article article = new Article();
        article.setAuthorId(authorId);
        article.setAuthorName(authorName);
        article.setContent(content);
        List<Article> list = articleService.getArticleList(article,commentContent,limit, offset);
        return list;
    }


}
