package book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.repository;

import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
