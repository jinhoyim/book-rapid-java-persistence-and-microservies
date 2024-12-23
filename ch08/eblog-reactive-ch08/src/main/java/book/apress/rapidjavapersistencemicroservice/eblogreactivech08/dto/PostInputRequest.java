package book.apress.rapidjavapersistencemicroservice.eblogreactivech08.dto;

import lombok.Data;

@Data
public class PostInputRequest {
    private String title;
    private String content;
}
