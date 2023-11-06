package me.employment.blog.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Paragraph {
    @Id
    private String title;
    private String description;
    @Column(columnDefinition = "text")
    private String text;
    private String image_url;
    @ManyToOne
    @JoinColumn(name = "article_title")
    private Article article;
}
