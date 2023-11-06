package me.employment.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Article {
    @Id
    private String title;
    private String description;
    @OneToMany(mappedBy = "article")
    private Set<Paragraph> paragraphs = new HashSet<>();

}
