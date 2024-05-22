package com.stellatech.elopezo.ecommerce.api.products;

import com.stellatech.elopezo.ecommerce.api.products.dto.CreateProductRequestDto;
import com.stellatech.elopezo.ecommerce.api.products.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    public Product addProduct(CreateProductRequestDto product) {
       Product newProduct = Product.builder()
               .name(product.getName())
               .description(product.getDescription())
               .stock(product.getStock())
               .price(product.getPrice())
               .build();

         return productRepository.save(newProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        return productRepository.save(existingProduct);
    }

}
