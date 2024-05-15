package com.stellatech.elopezo.ecommerce.api.users;

import com.stellatech.elopezo.ecommerce.api.orders.Order;
import com.stellatech.elopezo.ecommerce.api.products.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username",
            length = 32,
            unique = true,
            nullable = false)
    private String username;

    @Column(name = "email",
            length = 64,
            unique = true,
            nullable = false)
    private String email;

    @Column(name = "password",
            nullable = false)
    private String password;

    @Column(length = 128)
    private String biography;


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

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
