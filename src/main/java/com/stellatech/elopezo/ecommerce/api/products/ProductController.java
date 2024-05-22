package com.stellatech.elopezo.ecommerce.api.products;

import com.stellatech.elopezo.ecommerce.api.products.dto.CreateProductRequestDto;
import com.stellatech.elopezo.ecommerce.api.products.dto.ProductDetailResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<ProductDetailResponseDto> getProducts() {
        Iterable<Product> products = productService.getProducts();

        return ProductDetailResponseDto.fromIterable(products);

    }

    @GetMapping("/{id}")
    public ProductDetailResponseDto getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return ProductDetailResponseDto.fromProduct(product);
    }

    @PostMapping
    public Product addProduct(@RequestBody CreateProductRequestDto product, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return productService.addProduct(product, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        productService.deleteProduct(id, userId);
    }

    @PutMapping("/{id}")
    public ProductDetailResponseDto updateProduct(@PathVariable Long id, @RequestBody CreateProductRequestDto product, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Product updatedProduct = productService.updateProduct(id, product, userId);
        return ProductDetailResponseDto.fromProduct(updatedProduct);
    }
}
