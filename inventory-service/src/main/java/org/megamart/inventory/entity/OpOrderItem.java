package org.megamart.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Order Item Entity
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
@Entity
@Table( name = "OP_ORDER_ITEM" )
public class OpOrderItem implements Serializable
{
    @EmbeddedId
    private OpOrderItemPK opOrderItemPK = new OpOrderItemPK();

    @Column( name = "NO_OF_UNITS" )
    private Integer noOfUnits;

    @MapsId( "orderId" )
    @ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.ALL } )
    @JoinColumn( name = "ORDER_ID", referencedColumnName = "ID" )
    private OpOrder opOrder;

    @MapsId( "itemId" )
    @ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.ALL } )
    @JoinColumn( name = "ITEM_ID", referencedColumnName = "ID" )
    private OpItem opItem;

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof OpOrderItem ) ) return false;
        OpOrderItem that = ( OpOrderItem ) o;
        return Objects.equals( opOrderItemPK, that.getOpOrderItemPK() )
                       && Objects.equals( noOfUnits, that.getNoOfUnits() )
                       && Objects.equals( opOrder, that.getOpOrder() )
                       && Objects.equals( opItem, that.getOpItem() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( opOrderItemPK, noOfUnits, opOrder, opItem );
    }
}
