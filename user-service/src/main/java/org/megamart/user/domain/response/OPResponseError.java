package org.megamart.user.domain.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response Error
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class OPResponseError extends Exception
{
    private OPHttpStatus httpStatus;
    private OPStatus status;
    private String message;
    private OPAPIError opApiError;
    private Exception exception;
    private OPErrorLayer errorLayer;
    private OPErrorSource errorSource;

    public OPResponseError( Exception exception )
    {
        this.status = OPStatus.ERROR;
        this.exception = exception;
        this.httpStatus = OPHttpStatus.INTERNAL_SERVER_ERROR;
        this.message = exception.getMessage();
        this.errorSource = OPErrorSource.SERVER_ERROR;
        this.errorLayer = OPErrorLayer.API_LAYER;
    }

    public OPResponseError( OPAPIError opApiError )
    {
        this.httpStatus = OPHttpStatus.BAD_REQUEST;
        this.status = OPStatus.ERROR;
        this.opApiError = opApiError;
        this.errorSource = OPErrorSource.CLIENT_ERROR;
        this.errorLayer = OPErrorLayer.API_LAYER;
    }

    public OPResponseError( OPErrorSource errorSource, OPAPIError opApiError, OPHttpStatus httpStatus )
    {
        this.httpStatus = httpStatus;
        this.status = OPStatus.ERROR;
        this.opApiError = opApiError;
        this.errorSource = errorSource;
        this.errorLayer = OPErrorLayer.API_LAYER;
    }
}
