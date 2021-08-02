package com.shopping.service.impl;

import com.shopping.model.Comment;
import com.shopping.repository.CommentRepository;
import com.shopping.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @Author Seyed Mohsen Hosseini
 * @Since 26 July 2021
 */

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
