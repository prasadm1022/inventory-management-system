package org.megamart.user.domain.enums;

/**
 * @author prasadm
 * @since 23 Feb 2021
 */

public enum UserType
{
    ADMIN( 1 ),
    USER( 2 );

    public final int code;

    UserType( int code )
    {
        this.code = code;
    }
}
