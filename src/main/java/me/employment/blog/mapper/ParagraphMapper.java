package me.employment.blog.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.employment.blog.entities.Article;
import me.employment.blog.entities.Paragraph;
import me.employment.blog.mapper.def.Mapper;
import me.employment.blog.repo.ArticleRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class ParagraphMapper  implements Mapper<Map<String,String>, Paragraph>  {
    private ArticleRepository articleRepository;
    @Override
    public Map<String, String> dto(Paragraph paragraph) {
        Map<String,String> dataObject = new HashMap<>();
        dataObject.put("title",paragraph.getTitle());
        dataObject.put("description",paragraph.getDescription());
        dataObject.put("text",paragraph.getText());
        dataObject.put("image",paragraph.getImage_url());

        return dataObject;
    }

    @Override
    public Paragraph entity(Map<String, String> dto) {
        Paragraph paragraph = new Paragraph();
        paragraph.setDescription(dto.get("description"));
        paragraph.setTitle(dto.get("title"));
        paragraph.setText(dto.get("text"));
        paragraph.setImage_url(dto.get("image"));
        try{
            paragraph.setArticle(articleRepository.findById(dto.get("art_id")).orElseThrow());
            return paragraph;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
