package com.hospital.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "bill_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billitems {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "item_id")
        private Integer itemId;

        @ManyToOne
        @JoinColumn(name = "bill_id", nullable = false)
        private Billing billing;

        @Column(name = "description", nullable = false, length = 255)
        private String description;

        @Column(name = "quantity")
        private Integer quantity =1;

        @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
        private BigDecimal unitPrice;


        @Column(name = "total_price", precision = 10, scale = 2, insertable = false, updatable = false)
        private BigDecimal totalPrice;




}
