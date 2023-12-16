package org.megamart.inventory.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Order Item DTO
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class OrderItem
{
    private long orderId;
    private long itemId;
    private int noOfUnits;

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof OrderItem ) ) return false;
        OrderItem that = ( OrderItem ) o;
        return Objects.equals( orderId, that.getOrderId() )
                       && Objects.equals( itemId, that.getItemId() )
                       && Objects.equals( noOfUnits, that.getNoOfUnits() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( orderId, itemId, noOfUnits );
    }
}
