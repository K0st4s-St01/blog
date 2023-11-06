package me.employment.blog.rest;

import lombok.AllArgsConstructor;
import me.employment.blog.service.ArticleService;
import me.employment.blog.service.ParagraphService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ArticleRest {
    private ParagraphService paragraphService;
    private ArticleService articleService;

    @GetMapping("/article")
    public List<Map<String,String>> getArticle(){
        return articleService.get();
    }

    @GetMapping("/article/{art_id}")
    public List<Map<String,String>> getArticle(@PathVariable("art_id") String title){
        return articleService.get(title);
    }

    @PostMapping("/article")
    public Map<String,String> addArticle(@RequestBody Map<String,String> dto){
        return articleService.add(dto);
    }
    ///PARAGRAPHS

    @PostMapping("/article/{art_id}/paragraph")
    public Map<String,String> addParagraphToArticle(@RequestBody Map<String,String> dto,@PathVariable("art_id") String art_id)
    {
        dto.put("art_id",art_id);
        return paragraphService.add(dto);
    }
    @PutMapping("/article/{art_id}/paragraph")
    public Map<String,String> editParagraphToArticle(@RequestBody Map<String,String> dto,@PathVariable("art_id") String art_id){
        dto.put("art_id",art_id);
        return paragraphService.edit(art_id,dto);
    }

    @DeleteMapping("/article/{art_id}/paragraph")
    public Map<String,String> deleteParagraphToArticle(@RequestBody Map<String,String> dto,@PathVariable("art_id") String art_id){
        return paragraphService.delete(art_id);
    }

    @GetMapping ("/article/{art_id}/paragraph")
    public List<Map<String,String>> getArticleParagraphs(@PathVariable("art_id") String title){
        return paragraphService.get().stream().filter( p -> p.get("art_id").equals(title)).toList();
    }

    @GetMapping ("/article/{art_id}/paragraph/{p_id}")
    public List<Map<String,String>> getArticleParagraph(@PathVariable("art_id") String title , @PathVariable("p_id") String p_title){
        return paragraphService.get().stream().filter( p -> (p.get("art_id").equals(title) && p.get(title).equals(p_title))).toList();
    }
    //PARAGRAPHS END

    @PutMapping("/article/{art_id}")
    public Map<String,String> editArticle(@PathVariable("art_id") String title,@RequestBody Map<String,String> dto) {
        return articleService.edit(title, dto);
    }

    @DeleteMapping("/article/{art_id}")
    public Map<String,String> deleteArticle(@PathVariable("art_id") String title,@RequestBody Map<String,String> dto) {
        return articleService.delete(title);
    }



}
