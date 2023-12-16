package org.megamart.inventory.mapper;

import org.megamart.inventory.domain.dto.Customer;
import org.megamart.inventory.entity.OpCustomer;
import org.megamart.inventory.entity.OpOrder;
import org.megamart.inventory.repository.OpOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Customer Mapper
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Component
public class CustomerMapper
{
    @Autowired
    private OpOrderRepository orderRepository;

    public Customer toDto( OpCustomer opCustomer )
    {
        Customer customer = new Customer();

        customer.setId( opCustomer.getId() );
        customer.setPhoneNo( opCustomer.getPhoneNo() );
        customer.setName( opCustomer.getName() );
        customer.setAddress( opCustomer.getAddress() );
        customer.setEmail( opCustomer.getEmail() );
        customer.setNic( opCustomer.getNic() );

        // map orders
        customer.setOrderIdList( opCustomer.getOpOrders().stream().map( OpOrder::getId ).collect( Collectors.toList() ) );

        return customer;
    }

    public OpCustomer toEntity( Customer customer )
    {
        OpCustomer opCustomer = new OpCustomer();

        opCustomer.setId( customer.getId() );
        opCustomer.setPhoneNo( customer.getPhoneNo() );
        opCustomer.setName( customer.getName() );
        opCustomer.setAddress( customer.getAddress() );
        opCustomer.setEmail( customer.getEmail() );
        opCustomer.setNic( customer.getNic() );

        // map orders
        for( Long orderId : customer.getOrderIdList() )
        {
            Optional<OpOrder> optionalOpOrder = orderRepository.findById( orderId );
            optionalOpOrder.ifPresent( opOrder -> opCustomer.getOpOrders().add( opOrder ) );
        }

        return opCustomer;
    }
}
