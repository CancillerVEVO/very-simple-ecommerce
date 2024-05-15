package com.stellatech.elopezo.ecommerce.api.orders;

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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;


    @ManyToOne()
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_orders_users")
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

    @OneToMany(mappedBy = "order")
    private Set<OrderItems> orderItems;

}
