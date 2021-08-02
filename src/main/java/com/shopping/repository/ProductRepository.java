package com.shopping.repository;

import com.shopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);
    Product findByName(String name);
    List<Product> findAllByOrderByIdAsc();
    List<Product> findAllByCategoryId(long categoryId);
    List<Product> findProductsByDescriptionContaining(String str);
    List<Product> findProductsByNameContaining(String str);
    List<Product> findAllByPriceBetween(BigDecimal since, BigDecimal until);
}
