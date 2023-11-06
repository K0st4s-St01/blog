package me.employment.blog.mapper;

import me.employment.blog.entities.Article;
import me.employment.blog.mapper.def.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ArticleMapper implements Mapper<Map<String,String>, Article> {
    @Override
    public Map<String, String> dto(Article article) {
        Map<String,String> dataObject = new HashMap<>();
        dataObject.put("title",article.getTitle());
        dataObject.put("description",article.getDescription());

        return dataObject;
    }

    @Override
    public Article entity(Map<String, String> dto) {
        Article article = new Article();
        article.setDescription(dto.get("description"));
        article.setTitle(dto.get("title"));

        return article;
    }
}
