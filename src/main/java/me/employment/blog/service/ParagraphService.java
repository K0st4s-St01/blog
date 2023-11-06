package me.employment.blog.service;

import lombok.AllArgsConstructor;
import me.employment.blog.entities.Paragraph;
import me.employment.blog.mapper.ParagraphMapper;
import me.employment.blog.repo.ParagraphRepository;
import me.employment.blog.service.def.AEDGservice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ParagraphService implements AEDGservice<String,Map<String,String>> {
    private ParagraphMapper projectMapper;
    private ParagraphRepository paragraphRepository;

    @Override
    public Map<String, String> add(Map<String, String> dto) {
        HashMap<String,String> result = new HashMap<>();
        if(paragraphRepository.existsById(dto.get("title"))){
            result.put("state","error article instance already exists");
            return result;
        }
        Paragraph entity = projectMapper.entity(dto);
        paragraphRepository.save(entity);
        dto.put("state","article instance saved successfully");
        return dto;
    }

    @Override
    public Map<String, String> edit(String s, Map<String, String> dto) {
        HashMap<String,String> result = new HashMap<>();
        if(paragraphRepository.existsById(dto.get("title"))){
            dto.put("art_id",s);
            Paragraph entity = projectMapper.entity(dto);
            paragraphRepository.save(entity);
            dto.put("state","article instance saved successfully");
            return dto;
        }
        result.put("state","error article instance already exists");
        return result;
    }

    @Override
    public Map<String, String> delete(String s) {
        if(paragraphRepository.existsById(s)){
            paragraphRepository.deleteById(s);
            return Map.of("state","success "+s+" deleted");
        }else{
            return Map.of("state","error "+s+" not found");
        }
    }

    @Override
    public List<Map<String, String>> get() {
        List<Map<String,String>> result = new ArrayList<>();
        for(Paragraph instance : paragraphRepository.findAll()){
            result.add(projectMapper.dto(instance));
        }
        return result;
    }

    @Override
    public List<Map<String, String>> get(String s) {
        List<Map<String,String>> result = new ArrayList<>();
        if(!paragraphRepository.existsById(s)){
            return result;
        }
        Paragraph instance = paragraphRepository.findById(s).orElseThrow();
        result.add(projectMapper.dto(instance));
        return result;
    }
}
