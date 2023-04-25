package org.check.something.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "last_update")
    private Date lastUpdate;

    private int stock;
}
