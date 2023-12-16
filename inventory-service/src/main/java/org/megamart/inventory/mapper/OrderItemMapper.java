package org.megamart.inventory.mapper;

import org.megamart.inventory.domain.dto.OrderItem;
import org.megamart.inventory.entity.OpOrderItem;
import org.springframework.stereotype.Component;

/**
 * Order Item Mapper
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Component
public class OrderItemMapper
{
    public OrderItem toDto( OpOrderItem opOrderItem )
    {
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderId( opOrderItem.getOpOrderItemPK().getOrderId() );
        orderItem.setItemId( opOrderItem.getOpOrderItemPK().getItemId() );
        orderItem.setNoOfUnits( opOrderItem.getNoOfUnits() );

        return orderItem;
    }

    public OpOrderItem toEntity( OrderItem orderItem )
    {
        OpOrderItem opOrderItem = new OpOrderItem();

        opOrderItem.getOpOrderItemPK().setOrderId( orderItem.getOrderId() );
        opOrderItem.getOpOrderItemPK().setItemId( orderItem.getItemId() );
        opOrderItem.setNoOfUnits( orderItem.getNoOfUnits() );

        return opOrderItem;
    }
}
