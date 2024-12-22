package book.apress.rapidjavapersistencemicroservice.ebloggraphqlch07.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.Set;

@Entity
@ToString(exclude = {"posts"})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NON_ACTIVE;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private Set<Post> posts;

    protected Author() {

    }

    public Author(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
