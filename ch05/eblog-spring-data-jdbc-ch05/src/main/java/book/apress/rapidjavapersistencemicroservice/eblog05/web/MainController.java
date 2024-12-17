package book.apress.rapidjavapersistencemicroservice.eblog05.web;

import book.apress.rapidjavapersistencemicroservice.eblog05.model.Author;
import book.apress.rapidjavapersistencemicroservice.eblog05.model.Post;
import book.apress.rapidjavapersistencemicroservice.eblog05.repository.AuthorRepository;
import book.apress.rapidjavapersistencemicroservice.eblog05.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Slf4j
public class MainController {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> recentPosts() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/authors")
    public List<Author> authorsWithTopPosts() {
        return authorRepository.findAuthorsWithPosts();
    }
}
