package com.stellatech.elopezo.ecommerce.api.products;

import com.stellatech.elopezo.ecommerce.api.products.dto.CreateProductRequestDto;
import com.stellatech.elopezo.ecommerce.api.products.exceptions.ProductNotFoundException;
import com.stellatech.elopezo.ecommerce.api.users.User;
import com.stellatech.elopezo.ecommerce.api.users.UserService;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException("Producto no encontrado"));
    }

    public Product addProduct(CreateProductRequestDto product, Long userId) {
        User user = userService.getById(userId);

        Product newProduct = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .user(user)
                .build();

        return productRepository.save(newProduct);

    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException("Producto no encontrado"));

        productRepository.delete(product);
    }

public Product updateProduct(Long id, CreateProductRequestDto product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Producto no encontrado")
        );

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        return productRepository.save(existingProduct);
    }

}
