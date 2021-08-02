package com.shopping.controller;

import com.shopping.model.Comment;
import com.shopping.model.Product;
import com.shopping.service.CategoryService;
import com.shopping.service.CommentService;
import com.shopping.service.ProductService;
import com.shopping.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Seyed Mohsen Hosseini
 * @Since 01 August 2021
 */
@CrossOrigin
@Controller
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;
    private final ProductService productService;

    @Autowired
    public CommentController(CommentService commentService,ProductService productService) {
        this.commentService = commentService;
        this.productService = productService;

    }

//    @PostMapping("/product/new")
//    public String newProduct(@ModelAttribute("productForm") Product productForm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            logger.error(String.valueOf(bindingResult.getFieldError()));
//            model.addAttribute("method", "new");
//            return "product";
//        }
//        productService.save(productForm);
//        logger.debug(String.format("Product with id: %s successfully created.", productForm.getId()));
//
//        return "redirect:/home";
//    }



    @GetMapping("/comment/add/{id}")
    public String addComment(@PathVariable("id") long productId, Model model){
        Product product = productService.findById(productId);

        Comment  newComment=new Comment();
        newComment.setProduct(product);

        if (product != null){
            List<Comment> commentList=productService.findAllComments(productId);
            model.addAttribute("newComment", newComment);
            model.addAttribute("commentList", commentList);
            model.addAttribute("method", "edit");
            return "comment";
        }else {
            return "error/404";
        }
    }

//    @GetMapping("/comment/add/{id}")
//    public String addComment(@PathVariable("id") long productId, Model model){
//        Product product = productService.findById(productId);
//
//        if (product != null){
//            List<Comment> commentList=productService.findAllComments(productId);
//            model.addAttribute("productForm", product);
//            model.addAttribute("commentList", commentList);
//            model.addAttribute("method", "edit");
//            return "comment";
//        }else {
//            return "error/404";
//        }
//    }

    @PostMapping("/comment/add/{id}")
    public String addComment(@PathVariable("id") long productId, @ModelAttribute("productForm") Comment comment, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "edit");
            return "product";
        }
        commentService.save(comment);
//        productService.edit(productId, productForm);
        logger.debug(String.format("Product with id: %s has been successfully edited.", productId));

        return "redirect:/home";
    }

}
