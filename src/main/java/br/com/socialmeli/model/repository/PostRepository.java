package br.com.socialmeli.model.repository;

import br.com.socialmeli.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>
{
    List<Post> findByUserIdAndDateAfter(Long userid, Date date);
}
