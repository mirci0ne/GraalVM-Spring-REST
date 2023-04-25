package org.check.something.mappers;

import org.check.something.dtos.OrdersDto;
import org.check.something.entities.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrdersMapper {

    OrdersMapper MAPPER = Mappers.getMapper(OrdersMapper.class);

    @Mappings({
            @Mapping(target = "orderProduct", source = "orderProduct"),
            @Mapping(target = "orderDate", source = "orderDate"),
            @Mapping(target = "clientFirstName", source = "clientFirstName"),
            @Mapping(target = "clientLastName", source = "clientLastName"),
            @Mapping(target = "amount", source = "amount"),
    })
    Orders ordersDtoToOrders(OrdersDto ordersDto);
}
