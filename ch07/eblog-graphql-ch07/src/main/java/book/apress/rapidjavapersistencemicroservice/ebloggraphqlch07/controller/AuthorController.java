package book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.controller;

import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.dto.AuthorInputRequest;
import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.model.Author;
import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.repository.AuthorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Author> authorsWithTopPosts() {
        return authorRepository.findAuthorsWithPosts();
    }

    @MutationMapping
    public Author newAuthor(@Argument AuthorInputRequest input) {
        Author author = input.toAuthor();
        return authorRepository.save(author);
    }
}
