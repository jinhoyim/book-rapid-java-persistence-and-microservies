package book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.dto;

import lombok.Data;

@Data
public class PostInputRequest {

    private String title;

    private String content;

    private Long authorId;
}
