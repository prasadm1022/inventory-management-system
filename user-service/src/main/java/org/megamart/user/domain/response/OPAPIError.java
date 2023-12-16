package org.megamart.user.domain.response;

/**
 * Custom API Error Messages
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public enum OPAPIError
{
    NO_ERROR( 0, "", "" ),

    // inventory
    OP_ERR_INVENTORY_ITEM_SEARCH_ERROR( 10000, "Inventory item search failed", "Search failed" ),
    OP_ERR_INVENTORY_ITEM_SAVE_ERROR( 10001, "Inventory item save failed", "Save failed" ),
    OP_ERR_INVENTORY_ITEM_DELETE_ERROR( 10002, "Inventory item delete failed", "Delete failed" ),

    EXCP_SAVING_SQL_EXCEPTION( 900, "Saving SQL Exception", "Exception" ),
    EXCP_REMOTE_EXCEPTION( 901, "Remote method invocation Exception", "Exception" ),
    EXCP_NULLPOINT_EXCEPTION( 902, "NullPointer Exception", "Exception" ),
    EXCP_NUMBER_FORMAT_EXCEPTION( 903, "Number Format Exception", "Exception" ),
    EXCP_ILLEGAL_ARGUMENT_EXCEPTION( 904, "IllegalArgument Exception", "Exception" ),
    EXCP_INDEX_OUT_OF_BOUNDS_EXCEPTION( 905, "IndexOutOfBounds Exception", "Exception" ),
    EXCP_NO_SUCH_METHOD_EXCEPTION( 906, "NoSuchMethod Exception", "Exception" ),
    EXCP_ILLEGAL_ACCESS_EXCEPTION( 907, "IllegalAccess Exception", "Exception" ),
    EXCP_INVOCATION_TARGET_EXCEPTION( 908, "InvocationTarget Exception", "Exception" ),
    BAD_REQUEST_EXCEPTION( 909, "Bad Request Exception", "Exception" ),
    INVALID_DATA_ACCESS_RESOURCE_EXCEPTION( 910, "Invalid Data Access Resource Usage Exception", "Exception" ),
    INVALID_REQUEST( 998, "Invalid Request", "Exception" ),
    EXCP_UNKNOWN_EXCEPTION( 999, "Unknown Exception", "Exception" );

    public final int code;
    public final String details;
    public final String message;

    OPAPIError( int code, String details, String message )
    {
        this.code = code;
        this.details = details;
        this.message = message;
    }
}
