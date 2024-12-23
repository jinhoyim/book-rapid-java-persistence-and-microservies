package book.apress.rapidjavapersistencemicroservice.eblogreactivech08.repository;

import book.apress.rapidjavapersistencemicroservice.eblogreactivech08.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveCrudRepository<Post, Long> {
    Flux<Post> findAllBy(Pageable pageable);
}
