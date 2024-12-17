package book.apress.rapidjavapersistencemicroservice.eblog05.repository;

import book.apress.rapidjavapersistencemicroservice.eblog05.model.Author;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends Repository<Author, Long> {

    @Query("SELECT a.* FROM Author a")
    List<Author> findAuthorsWithPosts();

    @Query("SELECT * from Author where age=:age")
    List<Author> findByAge(@Param("age") Integer age);
}
