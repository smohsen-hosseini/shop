package com.shopping.service;

import com.shopping.model.Comment;
import com.shopping.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void save(Product product);
    void edit(long id, Product newProduct);
    void delete(long id);
    Product findById(long id);
    List<Comment> findAllComments(long id);
    List<Product> findAllByOrderByIdAsc();
    List<Product> findAllByCategoryId(long categoryId);
    long count();
    Product findByName(String name);
    List<Product> searchProducts(String searchStr);
    List<Product> searchProductsByPrice(BigDecimal min, BigDecimal max);

}
