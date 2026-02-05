package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "store_stocks", uniqueConstraints = {@UniqueConstraint(columnNames = {"stores_id", "goods_id"})})
@Data
public class StoreStocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stores_id", nullable = false)
    private Integer storesId;

    @Column(name = "goods_id", nullable = false)
    private Integer goodsId;

    @Column(nullable = false)
    private Integer quantity = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "stores_id", nullable = false,insertable = false, updatable = false)
    private Stores store;

    @ManyToOne
    @JoinColumn(name = "goods_id", nullable = false,insertable = false, updatable = false)
    private Goods goods;
}
