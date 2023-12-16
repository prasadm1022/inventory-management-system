package org.megamart.inventory.domain.response;

/**
 * Status
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public enum OPStatus
{
    SUCCESS( 1, "Success" ),
    WARNING( 0, "Warning" ),
    ERROR( -1, "Error" );

    public final int code;
    public final String message;

    OPStatus( int code, String message )
    {
        this.code = code;
        this.message = message;
    }
}
