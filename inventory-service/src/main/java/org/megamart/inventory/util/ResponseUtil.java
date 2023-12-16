package org.megamart.inventory.util;

import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseWrapper;
import org.springframework.http.ResponseEntity;

/**
 * Response Util
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public class ResponseUtil
{
    public static <T> ResponseEntity<OPResponseWrapper<T>> wrap( OPResponse<T> response )
    {
        OPResponseWrapper<T> responseWrapper = new OPResponseWrapper<>( response );
        return ResponseEntity.status( responseWrapper.getHttpStatus().value ).body( responseWrapper );
    }

    public static <T> ResponseEntity<OPResponseWrapper<T>> wrap( Exception error )
    {
        OPResponseWrapper<T> responseWrapper = new OPResponseWrapper<>( error );
        return ResponseEntity.status( responseWrapper.getHttpStatus().value ).body( responseWrapper );
    }

    private ResponseUtil()
    {
    }
}
