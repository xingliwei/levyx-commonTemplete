package com.xlw.levyx.api.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.squareup.okhttp.MediaType;
import com.xlw.levyx.model.Article;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by levyx on 2017/7/25.
 */
@Component
public class ArticleClient {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String url;
    private Logger logger = Logger.getLogger(ArticleClient.class);

    public void setUrl(String url) {
        this.url = url;
    }

    private ArticleClientHessian getHessian(){

        HessianProxyFactory factory = new HessianProxyFactory();

        ArticleClientHessian hessian = null;

        try {
            hessian = (ArticleClientHessian) factory.create(ArticleClientHessian.class, url + "/hessian");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.error("get hessian error.", e);
        }

        return hessian;
    }

    public String helloHessian(){
        return getHessian().hello(" from client...");
    }

    public Article getArticle(String id){
        return getHessian().getArticle(id);
    }

    public void saveArticle(Article article){
        getHessian().save(article);
    }

    public List<Article> getArticleList(Article article,String commentContent,Integer limit,Integer offset){
        return getHessian().getArticleList(article,commentContent,limit,offset);
    }
}
