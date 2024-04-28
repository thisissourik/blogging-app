package com.thisissourik.blog.repositories;

import com.thisissourik.blog.entitites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
