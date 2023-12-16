package org.megamart.inventory.domain.enums;

/**
 * Sort Direction
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public enum SortDirection
{
    ASC( "asc" ),
    DESC( "desc" );

    private final String direction;

    SortDirection( String direction )
    {
        this.direction = direction;
    }
}
