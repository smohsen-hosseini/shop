package com.shopping.controller;

import com.shopping.model.Product;
import com.shopping.service.CategoryService;
import com.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/","/index","/home"})
    public String home(Model model){
        model.addAttribute("products", getAllProducts());
        model.addAttribute("productsCount", productsCount());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    @RequestMapping("/searchByCategory")
    public String homePost(@RequestParam("categoryId") long categoryId, Model model){
        var products=productService.findAllByCategoryId(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("productsCount", products.size());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    @GetMapping(value = {"/searchByProduct"})
    public String searchByProduct( @RequestParam(value="searchStr",required=true) String searchStr, Model model){
        var products=searchProducts(searchStr);
        model.addAttribute("products", products);
        model.addAttribute("productsCount", products.size());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    @GetMapping(value = {"/searchByPrice"})
    public String searchByPrice( @RequestParam(value="minPrice",required=true) double minPrice,@RequestParam(value="maxPrice",required=true) double  maxPrice, Model model){
        var products=searchProductsByPrice(BigDecimal.valueOf(minPrice),BigDecimal.valueOf(maxPrice));
        model.addAttribute("products", products);
        model.addAttribute("productsCount", products.size());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    private List<Product> getAllProducts(){
        return productService.findAllByOrderByIdAsc();
    }

    private List<Product> searchProducts(String str){
        return productService.searchProducts(str);
    }
    private List<Product> searchProductsByPrice(BigDecimal min, BigDecimal max){
        return productService.searchProductsByPrice(min,max);
    }

    private long productsCount(){
        return productService.count();
    }
}
