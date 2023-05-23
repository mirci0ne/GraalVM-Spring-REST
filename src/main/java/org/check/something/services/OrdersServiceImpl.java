package org.check.something.services;

import lombok.RequiredArgsConstructor;
import org.check.something.dtos.OrdersDto;
import org.check.something.entities.Clients;
import org.check.something.entities.Orders;
import org.check.something.entities.Product;
import org.check.something.mappers.ClientsMapper;
import org.check.something.mappers.OrdersMapper;
import org.check.something.repository.ClientsRepository;
import org.check.something.repository.OrdersRepository;
import org.check.something.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl {

    private final OrdersRepository ordersRepository;
    private final ClientsRepository clientsRepository;
    private final ProductRepository productRepository;

    public Orders save(OrdersDto ordersDto) {
        Product product = productRepository.getIdByProductName(ordersDto.getOrderProduct());
        Objects.requireNonNull(product, "Product not found");
        product.setStock(product.getStock() - ordersDto.getAmount());
        productRepository.save(product);
        Clients clientsDto = ClientsMapper.MAPPER.ordersToClients(ordersDto);
        Orders orders = OrdersMapper.MAPPER.ordersDtoToOrders(ordersDto);
        clientsRepository.save(clientsDto);
        return ordersRepository.save(orders);
    }

    public Optional<Orders> getOrder(Long id) {
        return ordersRepository.findById(id);
    }

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    public void deleteOrder(Long id) {
        clientsRepository.deleteById(id);
        ordersRepository.deleteById(id);
    }

}
