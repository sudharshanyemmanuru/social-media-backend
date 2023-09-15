package com.socialmediaapp.backendrestapi.repository;

import com.socialmediaapp.backendrestapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    public Post findBypostId(int postId);
}
