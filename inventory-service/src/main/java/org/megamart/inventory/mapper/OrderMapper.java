package org.megamart.inventory.mapper;

import org.megamart.inventory.domain.dto.Order;
import org.megamart.inventory.domain.dto.OrderItem;
import org.megamart.inventory.domain.enums.OrderStatus;
import org.megamart.inventory.entity.OpCustomer;
import org.megamart.inventory.entity.OpItem;
import org.megamart.inventory.entity.OpOrder;
import org.megamart.inventory.entity.OpOrderItem;
import org.megamart.inventory.repository.OpCustomerRepository;
import org.megamart.inventory.repository.OpItemRepository;
import org.megamart.inventory.repository.OpOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Order Mapper
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Component
public class OrderMapper
{
    @Autowired
    private OpOrderRepository orderRepository;

    @Autowired
    private OpItemRepository itemRepository;

    @Autowired
    private OpCustomerRepository customerRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public Order toDto( OpOrder opOrder )
    {
        Order order = new Order();

        order.setId( opOrder.getId() );
        order.setTotalPrice( opOrder.getTotalPrice() );
        order.setDeliveryArea( opOrder.getDeliveryArea() );
        order.setDeliveryService( opOrder.getDeliveryService() );
        order.setDispatchDate( opOrder.getDispatchDate() );
        order.setStatus( opOrder.getStatus() );
        order.setCustomerId( opOrder.getOpCustomer().getId() );

        // map order items
        List<OrderItem> orderItems = opOrder.getOpOrderItems().stream().map( orderItemMapper::toDto ).collect( Collectors.toList() );
        for( OrderItem orderItem : orderItems )
        {
            order.getItems().put( orderItem.getItemId(), orderItem.getNoOfUnits() );
        }

        return order;
    }

    public OpOrder toNewEntity( Order order )
    {
        OpOrder opOrder = new OpOrder();

        opOrder.setId( order.getId() );
        opOrder.setTotalPrice( order.getTotalPrice() );
        opOrder.setDeliveryArea( order.getDeliveryArea() );
        opOrder.setDeliveryService( order.getDeliveryService() );
        opOrder.setDispatchDate( order.getDispatchDate() );
        opOrder.setStatus( order.getStatus() == null ? OrderStatus.PENDING : order.getStatus() );

        // map customer & customer's orders
        Optional<OpCustomer> optionalOpCustomer = customerRepository.findById( order.getCustomerId() );
        optionalOpCustomer.ifPresent( opOrder::setOpCustomer );

        // map order items
        order.getItems().forEach( ( itemId, noOfUnits ) ->
        {
            Optional<OpItem> optionalOpItem = itemRepository.findById( itemId );
            optionalOpItem.ifPresent( opItem ->
            {
                OpOrderItem opOrderItem = new OpOrderItem();
                opOrderItem.setNoOfUnits( noOfUnits );
                opOrderItem.setOpItem( opItem );
                opOrderItem.setOpOrder( opOrder );
                opOrder.getOpOrderItems().add( opOrderItem );
            } );
        } );

        return opOrder;
    }

    public OpOrder toUpdatedEntity( Order order )
    {
        Optional<OpOrder> optionalOpOrder = orderRepository.findById( order.getId() );

        OpOrder existingOrder = optionalOpOrder.orElseGet( OpOrder::new );
        existingOrder.setTotalPrice( order.getTotalPrice() );
        existingOrder.setDeliveryArea( order.getDeliveryArea() );
        existingOrder.setDeliveryService( order.getDeliveryService() );
        existingOrder.setDispatchDate( order.getDispatchDate() );
        existingOrder.setStatus( order.getStatus() );

        return existingOrder;
    }
}
