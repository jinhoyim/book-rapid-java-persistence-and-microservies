package book.apress.rapidjavapersistencemicroservice.eblogreactivech08.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Builder
@AllArgsConstructor
public class Post {

    @Id
    private Long id;

    private String title;

    @Column("content")
    private String content;

    protected Post() {

    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
