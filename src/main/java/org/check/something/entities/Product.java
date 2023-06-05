package org.check.something.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;

@Entity
@Table
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", unique = true)
    @NotBlank(message= "productName may not be empty")
    private String productName;

    @Column(name = "last_update")
    private Date lastUpdate;

    @NotNull(message= "stock may not be empty")
    @Range(min = 1)
    private Integer stock;

}
