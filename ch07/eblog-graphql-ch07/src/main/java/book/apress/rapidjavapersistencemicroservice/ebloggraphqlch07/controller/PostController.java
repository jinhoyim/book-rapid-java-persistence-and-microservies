package book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.controller;

import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.dto.PostInputRequest;
import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.model.Author;
import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.model.Post;
import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.repository.AuthorRepository;
import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostController(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Post> recentPosts(
            @Argument int limit,
            @Argument int offset,
            @Argument String orderBy) {
        PageRequest pageRequest = PageRequest.of(limit, offset, Sort.Direction.DESC, orderBy);
        return postRepository.findAll(pageRequest).getContent();
    }

    @MutationMapping
    public Post newPost(@Argument PostInputRequest input) {
        Author author = authorRepository.getReferenceById(input.getAuthorId());
        Post post = new Post(input.getTitle(), input.getContent(), author);
        return postRepository.save(post);
    }
}
