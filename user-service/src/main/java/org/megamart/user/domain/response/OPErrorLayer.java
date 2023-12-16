package org.megamart.user.domain.response;

/**
 * @author prasadm
 * @since 23 Feb 2021
 */

public enum OPErrorLayer
{
    API_LAYER( 0 ),
    TBX_LAYER( 1 );

    public final int code;

    private OPErrorLayer( int code )
    {
        this.code = code;
    }
}
