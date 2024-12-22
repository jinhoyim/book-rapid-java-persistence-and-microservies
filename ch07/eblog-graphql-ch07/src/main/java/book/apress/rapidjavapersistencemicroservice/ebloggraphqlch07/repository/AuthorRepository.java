package book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.repository;

import book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("Select distinct a from Author a left join fetch a.posts")
    List<Author> findAuthorsWithPosts();
}
