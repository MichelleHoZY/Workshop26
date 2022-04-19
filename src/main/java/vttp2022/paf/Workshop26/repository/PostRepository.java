package vttp2022.paf.Workshop26.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.Workshop26.model.Post;

import static vttp2022.paf.Workshop26.repository.Queries.*;

import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class PostRepository {
    
    @Autowired
    private JdbcTemplate template;

    public Optional<Post> getPostById(Integer postId) {
        return template.query(
            SQL_GET_POST_BY_ID,
            (ResultSet rs) -> {
                if(!rs.next())
                    return Optional.empty();
                final Post post = Post.populate(rs);
                return Optional.of(post);
            },
            postId
        );
    }

    public Integer insertPost(Post post) {

        Integer updCount = template.update(
            SQL_INSERT_POST, post.getImage(), post.getComment(), post.getPoster(), post.getImageType()
        );

        return updCount;

    }
    
}
