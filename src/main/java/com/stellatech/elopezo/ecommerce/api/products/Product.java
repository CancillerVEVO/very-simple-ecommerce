package com.stellatech.elopezo.ecommerce.api.products;

import com.stellatech.elopezo.ecommerce.api.orders.Order;
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

    @Column(name = "last_login_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            insertable = false
    )
    private LocalDateTime lastLoginAt;

    @Column(name = "deleted_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            insertable = false
    )
    private LocalDateTime deletedAt;

}
