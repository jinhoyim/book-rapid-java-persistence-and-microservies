package book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.dto;

import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.model.Author;
import lombok.Data;

@Data
public class AuthorInputRequest {

    private String name;

    private Integer age;

    public Author toAuthor() {
        return new Author(name, age);
    }
}
