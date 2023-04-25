package org.check.something.mappers;

import org.check.something.dtos.OrdersDto;
import org.check.something.entities.Clients;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientsMapper {

    ClientsMapper MAPPER = Mappers.getMapper(ClientsMapper.class);

    @Mappings({
            @Mapping(target = "firstName", source = "clientFirstName"),
            @Mapping(target = "lastName", source = "clientLastName"),
            @Mapping(target = "orderProduct", source = "orderProduct"),
            @Mapping(target = "orderDate", source = "orderDate"),
    })
    Clients ordersToClients(OrdersDto ordersDto);

}
