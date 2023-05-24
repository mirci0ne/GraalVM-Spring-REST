package org.check.something.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.check.something.dtos.OrdersDto;
import org.check.something.entities.Clients;
import org.check.something.entities.Orders;
import org.check.something.entities.Product;
import org.check.something.exceptions.ProductNotFoundException;
import org.check.something.mappers.ClientsMapper;
import org.check.something.mappers.OrdersMapper;
import org.check.something.repository.ClientsRepository;
import org.check.something.repository.OrdersRepository;
import org.check.something.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrdersServiceImpl {

    private final OrdersRepository ordersRepository;
    private final ClientsRepository clientsRepository;
    private final ProductRepository productRepository;

    public Orders save(OrdersDto ordersDto) {
        log.info("Saving order");
        Product product = productRepository.getIdByProductName(ordersDto.getOrderProduct());
        Optional.ofNullable(product).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setStock(product.getStock() - ordersDto.getAmount());
        productRepository.save(product);
        Clients clientsDto = ClientsMapper.MAPPER.ordersToClients(ordersDto);
        Orders orders = OrdersMapper.MAPPER.ordersDtoToOrders(ordersDto);
        clientsRepository.save(clientsDto);
        return ordersRepository.save(orders);
    }

    public Optional<Orders> getOrder(Long id) {
        log.info("Returning order for id: {}", id);
        return ordersRepository.findById(id);
    }

    public List<Orders> getOrders() {
        log.info("Returning orders");
        return ordersRepository.findAll();
    }

    public void deleteOrder(Long id) {
        log.info("Deleting order for id: {}", id);
        clientsRepository.deleteById(id);
        ordersRepository.deleteById(id);
    }

}
