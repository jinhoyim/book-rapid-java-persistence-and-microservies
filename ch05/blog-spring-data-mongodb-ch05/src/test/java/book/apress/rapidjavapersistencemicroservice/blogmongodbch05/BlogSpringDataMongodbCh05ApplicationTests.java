package book.apress.rapidjavapersistencemicroservice.blogmongodbch05;

import book.apress.rapidjavapersistencemicroservice.blogmongodbch05.model.mongo.Comment;
import book.apress.rapidjavapersistencemicroservice.blogmongodbch05.model.mongo.Post;
import book.apress.rapidjavapersistencemicroservice.blogmongodbch05.model.mongo.PostStatus;
import book.apress.rapidjavapersistencemicroservice.blogmongodbch05.repository.PostMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class BlogSpringDataMongodbCh05ApplicationTests {

	@Autowired
	PostMongoRepository postMongoRepository;

	@BeforeEach
	void setUp() {
		postMongoRepository.deleteAll();
	}

	@Test
	void testAddNewPosts() {
		List<Post> posts = createPosts();
		postMongoRepository.saveAll(posts);
		log.info("All objects: {}", postMongoRepository.findAll());
		Post blogByTitle = postMongoRepository.findByTitle("Blog Title1");
		assertEquals("Blog1", blogByTitle.getBlogName(), "Blog names do not match");
	}

	private List<Post> createPosts() {
		List<Post> posts = new ArrayList<>();
		IntStream.range(1,6).forEach(i -> {
			Post nextSamplePost = createNextSimplePost(i);
			posts.add(nextSamplePost);
		});
		return posts;
	}

	private Post createNextSimplePost(int i) {
		Post post = Post.builder()
				.blogName("Blog" + i)
				.title("Blog Title" + i)
				.content("Blog" + i + " content")
				.postStatus(PostStatus.ACTIVE)
				.author("User" + i)
				.build();
		Set<Comment> comments = new HashSet<>();
		comments.add(new Comment("Comment" + (i + 1)));
		comments.add(new Comment("Comment" + (i + 2)));
		post.setComments(comments);
		return post;
	}
}
