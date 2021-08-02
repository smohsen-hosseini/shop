package com.shopping.service.impl;

import com.shopping.model.Comment;
import com.shopping.model.Product;
import com.shopping.repository.CommentRepository;
import com.shopping.repository.ProductRepository;
import com.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,CommentRepository commentRepository){
        this.productRepository = productRepository;
        this.commentRepository=commentRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void edit(long id, Product newProduct) {
        Product found = productRepository.getOne(id);
        found.setName(newProduct.getName());
        found.setImageUrl(newProduct.getImageUrl());
        found.setDescription(newProduct.getDescription());
        found.setPrice(newProduct.getPrice());
        save(newProduct);
    }

    @Override
    public void delete(long id) {
        productRepository.delete(findById(id));
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Comment> findAllComments(long id) {
        return commentRepository.findAllByProduct(productRepository.findById(id));
    }

    @Override
    public List<Product> findAllByOrderByIdAsc() {
        return productRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Product> findAllByCategoryId(long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> searchProducts(String searchStr) {
        //return productRepository.findProductsByDescriptionContaining(searchStr);
        var listDesc = productRepository.findProductsByDescriptionContaining(searchStr);
        var listName = productRepository.findProductsByNameContaining(searchStr);
        Set<Product> retSet=new HashSet<>(listDesc);
        listName.forEach(product -> retSet.add(product));
        return new ArrayList<Product>(retSet);
    }
    @Override
    public List<Product> searchProductsByPrice(BigDecimal min, BigDecimal  max) {
        return productRepository.findAllByPriceBetween(min,max);
    }
}
