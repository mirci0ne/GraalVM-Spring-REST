package org.check.something.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;

@Entity
@Table
@Data
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message= "firstName may not be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message= "lastName may not be empty")
    private String lastName;

    @Column(name = "order_product")
    @NotBlank(message= "orderProduct may not be empty")
    private String orderProduct;

    @Column(name = "order_date")
    private Date orderDate;

}
