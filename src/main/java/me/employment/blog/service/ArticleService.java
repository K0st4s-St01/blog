package me.employment.blog.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.employment.blog.entities.Article;
import me.employment.blog.mapper.ArticleMapper;
import me.employment.blog.repo.ArticleRepository;
import me.employment.blog.service.def.AEDGservice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ArticleService implements AEDGservice<String,Map<String,String>> {
    private ArticleMapper articleMapper;
    private ArticleRepository articleRepository;

    @Override
    public Map<String, String> add(Map<String, String> dto) {
        HashMap<String,String> result = new HashMap<>();
        if(articleRepository.existsById(dto.get("title"))){
            result.put("state","error article instance already exists");
            return result;
        }
        Article entity = articleMapper.entity(dto);
        articleRepository.save(entity);
        dto.put("state","article instance saved successfully");
        return dto;
    }

    @Override
    public Map<String, String> edit(String s, Map<String, String> dto) {
        HashMap<String,String> result = new HashMap<>();
        if(articleRepository.existsById(dto.get("title"))){
            Article entity = articleMapper.entity(dto);
            articleRepository.save(entity);
            dto.put("state","article instance saved successfully");
            return dto;
        }
        result.put("state","error article instance already exists");
        return result;
    }

    @Override
    public Map<String, String> delete(String s) {
        if(articleRepository.existsById(s)){
            articleRepository.deleteById(s);
            return Map.of("state","success "+s+" deleted");
        }else{
            return Map.of("state","error "+s+" not found");
        }
    }

    @Override
    public List<Map<String, String>> get() {
        List<Map<String,String>> result = new ArrayList<>();
        for(Article instance : articleRepository.findAll()){
            result.add(articleMapper.dto(instance));
        }
        return result;
    }

    @Override
    public List<Map<String, String>> get(String s) {
        List<Map<String,String>> result = new ArrayList<>();
        if(!articleRepository.existsById(s)){
            return result;
        }
        Article instance = articleRepository.findById(s).orElseThrow();
        result.add(articleMapper.dto(instance));
        return result;
    }
}
