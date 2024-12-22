package book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString(exclude = {"author"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Author author;

    protected Post() {

    }

    public Post(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
        status = Status.ACTIVE;
    }
}
