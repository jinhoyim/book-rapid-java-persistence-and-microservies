package book.apress.rapidjavapersistencemicroservice.eblogreactivech08.web;

import book.apress.rapidjavapersistencemicroservice.eblogreactivech08.dto.PostInputRequest;
import book.apress.rapidjavapersistencemicroservice.eblogreactivech08.model.Post;
import book.apress.rapidjavapersistencemicroservice.eblogreactivech08.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/posts")
    public Mono<Post> newPost(@RequestBody PostInputRequest postInputRequest) {
        Post post = Post.builder()
                .title(postInputRequest.getTitle())
                .content(postInputRequest.getContent())
                .build();
        return postRepository.save(post);
    }

    @GetMapping("/posts")
    Flux<Post> allPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/recentPosts")
    public Flux<Post> recentPosts(
            @Param("limit") Integer limit,
            @Param("offset") Integer offset,
            @Param("orderBy") String orderBy
    ) {
        log.info("recentPosts, params: {}, {}", limit, offset);
        PageRequest pageRequest = PageRequest.of(offset / limit, limit, Sort.Direction.DESC, orderBy);
        return postRepository.findAllBy(pageRequest);
    }
}
