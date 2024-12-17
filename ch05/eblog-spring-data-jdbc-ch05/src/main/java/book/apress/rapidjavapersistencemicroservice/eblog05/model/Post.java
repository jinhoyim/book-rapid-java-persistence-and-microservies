package book.apress.rapidjavapersistencemicroservice.eblog05.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@ToString(exclude = {"author"})
@EqualsAndHashCode(exclude = {"author"})
public class Post {

    @Id
    private Long id;
    private String title;
    private String content;
}
