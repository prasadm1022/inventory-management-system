package org.megamart.user.domain.response;

/**
 * @author prasadm
 * @since 23 Feb 2021
 */

public enum OPErrorSource
{
    CLIENT_ERROR( 4 ),
    SERVER_ERROR( 5 ),
    PACKAGE_ERROR( 6 );

    public final int code;

    private OPErrorSource( int code )
    {
        this.code = code;
    }
}
