package book.apress.rapidjavapersistencemicroservice.blogmongodbch05.model.mongo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(exclude = {"comments"})
@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    private PostStatus postStatus;
    private String blogName;
    private String author;
    private Set<Comment> comments;
}
