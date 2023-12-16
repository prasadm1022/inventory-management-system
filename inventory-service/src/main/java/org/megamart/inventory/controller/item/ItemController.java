package org.megamart.inventory.controller.item;

import org.megamart.inventory.domain.dto.Item;
import org.megamart.inventory.domain.criteria.ItemSearchCriteria;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;
import org.megamart.inventory.domain.response.OPResponseWrapper;
import org.megamart.inventory.service.IItemService;
import org.megamart.inventory.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Item REST Controller
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@RestController
public class ItemController implements IItemController
{
    @Autowired
    private IItemService inventoryService;

    @Override
    public ResponseEntity<OPResponseWrapper<Item>> search( ItemSearchCriteria itemSearchCriteria )
    {
        try
        {
            OPResponse<Item> opResponse = inventoryService.searchItems( itemSearchCriteria );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<OPResponseWrapper<Item>> save( Item item )
    {
        try
        {
            OPResponse<Item> opResponse = inventoryService.saveItem( item );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<OPResponseWrapper<Void>> delete( long id )
    {
        try
        {
            OPResponse<Void> opResponse = inventoryService.deleteItem( id );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<OPResponseWrapper<Item>> updateQuantity( long id, int quantity )
    {
        try
        {
            OPResponse<Item> opResponse = inventoryService.updateQuantity( id, quantity );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }
}
