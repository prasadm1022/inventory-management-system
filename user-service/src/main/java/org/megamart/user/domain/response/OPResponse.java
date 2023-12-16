package org.megamart.user.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Response
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class OPResponse<T>
{
    private PageData pageData;
    private List<T> data;
    private OPHttpStatus httpStatus;
    private OPStatus status;
    private String message;
    private OPResponseError responseError;

    public OPResponse()
    {
        this.status = OPStatus.SUCCESS;
        this.pageData = new PageData();
        this.data = new ArrayList<>();
        this.httpStatus = OPHttpStatus.OK;
    }

    public OPResponse( OPStatus status )
    {
        this.status = status;
        this.pageData = new PageData();
        this.data = new ArrayList<>();
        if( status == OPStatus.SUCCESS )
        {
            this.httpStatus = OPHttpStatus.OK;
        }
        else if( status == OPStatus.ERROR )
        {
            this.httpStatus = OPHttpStatus.BAD_REQUEST;
        }
        else
        {
            this.httpStatus = OPHttpStatus.OK;
        }
    }

    public OPResponse( OPAPIError opApiError )
    {
        this.httpStatus = OPHttpStatus.BAD_REQUEST;
        this.status = OPStatus.ERROR;
        this.responseError = new OPResponseError( opApiError );
    }

    public OPResponse( T data )
    {
        this();
        this.data.add( data );
        this.pageData.setCount( 1 );
        this.pageData.setTotal( 1 );
    }

    public OPResponse( T data, String message )
    {
        this();
        this.data.add( data );
        this.pageData.setCount( 1 );
        this.pageData.setTotal( 1 );
        setMessage( message );
    }

    public OPResponse( List<T> data )
    {
        this();
        this.data.addAll( data );
        this.pageData.setCount( data.size() );
        this.pageData.setTotal( data.size() );
    }

    public OPResponse( List<T> data, String message )
    {
        this();
        this.data.addAll( data );
        this.pageData.setCount( data.size() );
        this.pageData.setTotal( data.size() );
        setMessage( message );
    }

    public OPResponse( List<T> data, int totalCount )
    {
        this();
        this.data.addAll( data );
        this.pageData.setCount( data.size() );
        this.pageData.setTotal( totalCount );
    }

    public OPResponse( List<T> data, int totalCount, String message )
    {
        this();
        this.data.addAll( data );
        this.pageData.setCount( data.size() );
        this.pageData.setTotal( totalCount );
        setMessage( message );
    }

    public boolean _isSuccess()
    {
        return status == OPStatus.SUCCESS;
    }

    public T _getFirstData()
    {
        if( this.data != null && !this.data.isEmpty() )
        {
            return this.data.get( 0 );
        }
        else return null;
    }
}
