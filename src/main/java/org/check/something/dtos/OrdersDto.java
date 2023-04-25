package org.check.something.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OrdersDto {

    private String orderProduct;
    private Date orderDate;
    private String clientFirstName;
    private String clientLastName;
    private int amount;
}
