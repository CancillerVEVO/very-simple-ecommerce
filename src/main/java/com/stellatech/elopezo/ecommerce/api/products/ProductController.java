package com.stellatech.elopezo.ecommerce.api.products;

import com.stellatech.elopezo.ecommerce.api.products.dto.CreateProductRequestDto;
import com.stellatech.elopezo.ecommerce.api.products.dto.ProductDetailResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
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

    @PostMapping("/create")
    public Product addProduct(@Valid @RequestBody CreateProductRequestDto product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
}
