package org.megamart.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Order Item PK Entity
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
@Embeddable
public class OpOrderItemPK implements Serializable
{
    @Column( name = "ORDER_ID" )
    private Long orderId;

    @Column( name = "ITEM_ID" )
    private Long itemId;

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof OpOrderItemPK ) ) return false;
        OpOrderItemPK that = ( OpOrderItemPK ) o;
        return Objects.equals( orderId, that.getOrderId() ) && Objects.equals( itemId, that.getItemId() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( orderId, itemId );
    }
}
