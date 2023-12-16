package org.megamart.inventory.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.megamart.inventory.domain.enums.OrderStatus;

import java.util.*;

/**
 * Order DTO
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class Order
{
    private long id;
    private double totalPrice;
    private String deliveryArea;
    private String deliveryService;
    private Date dispatchDate;
    private OrderStatus status;
    private long customerId;
    private Map<Long,Integer> items = new HashMap<>();

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof Order ) ) return false;
        Order that = ( Order ) o;
        return Objects.equals( id, that.getId() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id );
    }
}
