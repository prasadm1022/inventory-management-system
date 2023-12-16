package org.megamart.user.domain.response;

/**
 * HTTP Status
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

    private OPStatus( int code, String message )
    {
        this.code = code;
        this.message = message;
    }
}
