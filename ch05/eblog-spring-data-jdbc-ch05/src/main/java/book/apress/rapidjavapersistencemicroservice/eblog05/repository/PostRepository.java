package book.apress.rapidjavapersistencemicroservice.eblog05.repository;

import book.apress.rapidjavapersistencemicroservice.eblog05.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
