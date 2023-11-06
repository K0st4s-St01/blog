package me.employment.blog.repo;

import me.employment.blog.entities.Article;
import me.employment.blog.entities.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph,String> {
}
