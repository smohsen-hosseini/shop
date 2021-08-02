package com.shopping;

import com.shopping.model.Category;
import com.shopping.model.Comment;
import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.service.CategoryService;
import com.shopping.service.CommentService;
import com.shopping.service.ProductService;
import com.shopping.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MockData implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    private static final Logger logger = LogManager.getLogger(MockData.class);

    @Autowired
    public MockData(UserService userService, ProductService productService, CategoryService categoryService, CommentService commentService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    @Override
    public void run(String... args) {
        List<Category> categories = categoryService.findAll();
        if (categories.size() == 0) {
            adminAccount();
            userAccount();
            category();
            exampleProducts();
            rankAndCommentOnProduct();
        }
    }

    private void userAccount() {
        User user = new User();

        user.setUsername("user");
        user.setPassword("user");
        user.setBlocked(false);
        user.setPasswordConfirm("user");
        user.setGender("Male");
        user.setEmail("user@shopping.com");

        userService.save(user);
    }

    private void adminAccount() {
        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setBlocked(false);
        admin.setPasswordConfirm("admin");
        admin.setGender("Female");
        admin.setEmail("admin@shopping.com");

        userService.save(admin);
    }

    private void category() {
        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();

        category1.setId(1);
        category1.setCategoryName("Electronics");
        category2.setId(2);
        category2.setCategoryName("Art");
        category3.setId(3);
        category3.setCategoryName("Sports");

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);
    }

    private void exampleProducts() {

        Product productElectronic = new Product();
        Product productElectronic2 = new Product();
        Product productArt = new Product();
        Product productSport = new Product();

        productElectronic.setName("Smart Watch");
        productElectronic.setImageUrl("https://www.startech.com.bd/image/cache/catalog/smart-watch/xiaomi/haylou-rt-ls05s/haylou-rt-ls05s-01-500x500.jpeg");
        productElectronic.setDescription("RT LS05S Smart Watch comes with an excellent screen and long battery life at a low price. Sales start in March 2021. It featured with Online dial replacement, Heart rate monitoring, 12 sport modes, 20-day battery life.");
        productElectronic.setCategory(categoryService.findByCategoryName("Electronics"));
        productElectronic.setPrice(BigDecimal.valueOf(99.99));

        productElectronic2.setName("CANON EOS 750D");
        productElectronic2.setImageUrl("https://www.startech.com.bd/image/cache/catalog/DSLR/Canon/Canon%20EOS%20750D-1-500x500.jpg");
        productElectronic2.setDescription("Explore creative shooting angles and enjoy simple and intuitive access to controls using the 3.0 (7.7cm) Vari Angle LCD touch screen");
        productElectronic2.setCategory(categoryService.findByCategoryName("Electronics"));
        productElectronic2.setPrice(BigDecimal.valueOf(2788.50));


        productArt.setName("Flower");
        productArt.setImageUrl("https://i.pinimg.com/564x/12/e2/6d/12e26d72c68442640b27583cff8d50e7.jpg");
        productArt.setDescription("Irises is one of several paintings of irises by the Dutch artist Vincent van Gogh, and one of a series of paintings he made at the Saint Paul-de-Mausole asylum in Saint-Rémy-de-Provence, France.");
        productArt.setCategory(categoryService.findByCategoryName("Art"));
        productArt.setPrice(BigDecimal.valueOf(163.99));

        productSport.setName("BALL BASKET EASY");
        productSport.setImageUrl("https://www.dsgsport.it/WebRoot/StoreIT3/Shops/124324/5B8B/9D87/DFEE/435D/61D0/0A0A/B011/622C/pallone_basket_easy_0004_m.jpg");
        productSport.setDescription("Characteristics: 8 panels. Material: soft touch sponge rubber. Bladder: Natural rubber + butyl rubber. Printed logos. ");
        productSport.setCategory(categoryService.findByCategoryName("Sports"));
        productSport.setPrice(BigDecimal.valueOf(10.95));

        productService.save(productElectronic);
        productService.save(productElectronic2);
        productService.save(productArt);
        productService.save(productSport);
    }

    private void rankAndCommentOnProduct() {
        Comment commentSmartWatch = new Comment();
        commentSmartWatch.setComment("nice Smart Watch");
        commentSmartWatch.setRanking("4");
        commentSmartWatch.setProduct(productService.findByName("Smart Watch"));
        commentSmartWatch.setUser(userService.findByUsername("user"));

        Comment commentCamera = new Comment();
        commentSmartWatch.setComment("Best DOS Camera but Expensive");
        commentSmartWatch.setRanking("5");
        commentSmartWatch.setProduct(productService.findByName("CANON EOS 750D"));
        commentSmartWatch.setUser(userService.findByUsername("user"));

        Comment commentFlower = new Comment();
        commentFlower.setComment("well Flower Painting");
        commentFlower.setRanking("5");
        commentFlower.setProduct(productService.findByName("Flower"));
        commentFlower.setUser(userService.findByUsername("user"));

        Comment commentGlasses = new Comment();
        commentGlasses.setComment("best quality ball have ever seen!!!");
        commentGlasses.setRanking("3");
        commentGlasses.setProduct(productService.findByName("BALL BASKET EASY"));
        commentGlasses.setUser(userService.findByUsername("user"));

        commentService.save(commentSmartWatch);
        commentService.save(commentFlower);
        commentService.save(commentGlasses);
    }

}
