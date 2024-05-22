package com.stellatech.elopezo.ecommerce.api.products;

import com.stellatech.elopezo.ecommerce.api.order_items.OrderItems;
import com.stellatech.elopezo.ecommerce.api.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(length = 64, nullable = false)
    private String name;
    @Column(length = 128)
    private String description;
    private Integer stock;
    private Double price;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_products_users")
    )
    private User user;

    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP",
            insertable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            insertable = false
    )
    private LocalDateTime updatedAt;


    @Column(name = "deleted_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            insertable = false
    )
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "product")
    private Set<OrderItems> orderItems;


    // Builder Pattern
    public static class Builder {
        private String name;
        private String description;
        private Integer stock;
        private Double price;
        private User user;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder stock(Integer stock) {
            this.stock = stock;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setName(this.name);
            product.setDescription(this.description);
            product.setStock(this.stock);
            product.setPrice(this.price);
            product.setUser(this.user);
            return product;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
