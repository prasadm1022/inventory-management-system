package org.megamart.user.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Response Wrapper
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "status", "error", "pageData", "data", "version" } )
public class OPResponseWrapper<T>
{
    private static final String API_ERROR_CODE = "API Error Code : ";
    private static final String ERROR_CODE_SEPARATOR = " - ";
    private static final String VERSION = "v1.0";

    @Getter
    @Setter
    private PageData pageData = new PageData();

    @Getter
    @Setter
    private List<T> data;

    @Getter
    @Setter
    private Status status;

    @Getter
    @Setter
    private OPError error;

    @Getter
    @Setter
    private OPHttpStatus httpStatus;

    @Getter
    @Setter
    private String version = VERSION;

    public OPResponseWrapper( OPResponse<T> opResponse )
    {
        if( opResponse.getResponseError() != null )
        {
            onError( opResponse.getResponseError() );
        }
        else
        {
            this.status = new Status();
            if( opResponse.getStatus() != null )
            {
                this.status.code = opResponse.getStatus().code;
                this.status.message = opResponse.getStatus().message;
            }
            this.status.description = opResponse.getMessage();
            this.httpStatus = opResponse.getHttpStatus();
            this.data = opResponse.getData();
            this.pageData = opResponse.getPageData();
        }

    }

    public OPResponseWrapper( Exception exception )
    {
        onError( exception );
    }

    private static void addErrorDescription( OPAPIError errorCode, List<String> errors )
    {
        errors.add( API_ERROR_CODE + errorCode.code + ERROR_CODE_SEPARATOR + errorCode.details );
    }

    private void onError( Exception exception )
    {
        OPResponseError opResponseError;
        if( !( exception instanceof OPResponseError ) )
        {
            opResponseError = new OPResponseError( exception );
        }
        else
        {
            opResponseError = ( OPResponseError ) exception;
        }
        this.status = new Status();
        if( opResponseError.getStatus() != null )
        {
            this.status.code = opResponseError.getStatus().code;
            this.status.message = opResponseError.getStatus().message;
        }
        this.status.description = opResponseError.getMessage();
        this.httpStatus = opResponseError.getHttpStatus();
        this.pageData = null;
        this.data = null;
        fillErrorInfo( opResponseError );
    }

    private void fillErrorInfo( OPResponseError opResponseError )
    {
        mapErrorCode( opResponseError );
    }

    private void mapErrorCode( OPResponseError responseError )
    {
        OPError error = new OPError();
        long errorCode = ( long ) responseError.getErrorSource().code;
        errorCode = errorCode * 10L + ( long ) responseError.getErrorLayer().code;
        if( responseError.getOpApiError() == null && responseError.getException() != null )
        {
            Exception ex = responseError.getException();
            ex.printStackTrace();

            Throwable cause = ex.getCause();
            if( cause != null )
            {
                if( cause instanceof RemoteException )
                {
                    responseError.setOpApiError( OPAPIError.EXCP_REMOTE_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_REMOTE_EXCEPTION.message );
                }
                else if( cause instanceof NullPointerException )
                {
                    responseError.setOpApiError( OPAPIError.EXCP_NULLPOINT_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_NULLPOINT_EXCEPTION.message );
                }
                else if( cause instanceof NumberFormatException )
                {
                    responseError.setOpApiError( OPAPIError.EXCP_NUMBER_FORMAT_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_NUMBER_FORMAT_EXCEPTION.message );
                }
                else if( cause instanceof IllegalArgumentException )
                {
                    responseError.setOpApiError( OPAPIError.EXCP_ILLEGAL_ARGUMENT_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_ILLEGAL_ARGUMENT_EXCEPTION.message );
                }
                else if( cause instanceof IndexOutOfBoundsException )
                {
                    responseError.setOpApiError( OPAPIError.EXCP_INDEX_OUT_OF_BOUNDS_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_INDEX_OUT_OF_BOUNDS_EXCEPTION.message );
                }
                else if( cause instanceof NoSuchMethodException )
                {
                    responseError.setOpApiError( OPAPIError.EXCP_NO_SUCH_METHOD_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_NO_SUCH_METHOD_EXCEPTION.message );
                }
                else if( cause instanceof IllegalAccessException )
                {
                    responseError.setOpApiError( OPAPIError.EXCP_ILLEGAL_ACCESS_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_ILLEGAL_ACCESS_EXCEPTION.message );
                }
                else if( cause instanceof InvocationTargetException )
                {
                    responseError.setOpApiError( OPAPIError.EXCP_INVOCATION_TARGET_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_INVOCATION_TARGET_EXCEPTION.message );
                }
                else
                {
                    responseError.setOpApiError( OPAPIError.EXCP_UNKNOWN_EXCEPTION );
                    responseError.setMessage( OPAPIError.EXCP_UNKNOWN_EXCEPTION.message );
                }
            }
        }

        errorCode = errorCode * 10000L + ( long ) ( responseError.getOpApiError() == null ? 0 : responseError.getOpApiError().code );
        List<String> errors = new ArrayList<>( 2 );
        if( responseError.getOpApiError() != null && responseError.getOpApiError().code > 0 )
        {
            addErrorDescription( responseError.getOpApiError(), errors );
            error.setDetails( responseError.getOpApiError().details );
            error.setMessage( responseError.getOpApiError().message );
            if( this.status != null && ( this.status.description == null || this.status.description.isEmpty() ) )
            {
                this.status.description = responseError.getOpApiError().message;
            }
        }

        error.setErrors( errors );
        error.setCode( errorCode );
        this.setError( error );
    }

    @JsonInclude( JsonInclude.Include.NON_NULL )
    @Getter
    @Setter
    private static class Status
    {
        int code;
        String message;
        String description;
    }
}
