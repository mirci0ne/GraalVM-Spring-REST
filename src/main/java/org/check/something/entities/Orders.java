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
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_product")
    @NotBlank(message = "orderProduct may not be empty")
    private String orderProduct;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "client_first_name")
    @NotBlank(message = "clientFirstName may not be empty")
    private String clientFirstName;

    @Column(name = "client_last_name")
    @NotBlank(message = "clientLastName may not be empty")
    private String clientLastName;

    @Range(min = 1)
    @NotNull(message= "amount may not be empty")
    private Integer amount;

}
