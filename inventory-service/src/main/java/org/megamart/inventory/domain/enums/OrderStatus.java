package org.megamart.inventory.domain.enums;

/**
 * Order Status
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public enum OrderStatus
{
    DELIVERED( 1 ),
    PENDING( 2 ),
    RETURNED( 3 ),
    CANCELLED( 4 ),
    LOST( 5 );

    public final int code;

    OrderStatus( int code )
    {
        this.code = code;
    }
}
