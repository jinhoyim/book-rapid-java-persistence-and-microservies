package book.apress.rapidjavapersistencemicroservice.eblog05.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.Set;

@Data
@AllArgsConstructor
@ToString(exclude = {"posts"})
@EqualsAndHashCode(exclude = {"posts"})
public class Author {

    @Id
    private Long id;
    private String name;
    private int age;

    private Set<Post> posts;

    @PersistenceCreator
    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Author withId(Long id) {
        return new Author(id, name, age, posts);
    }
}
