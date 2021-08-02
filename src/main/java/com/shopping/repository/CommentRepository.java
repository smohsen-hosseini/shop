package com.shopping.repository;

import com.shopping.model.Comment;
import com.shopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findById(long id);
    List<Comment> findAllByProduct(Product product);

}
