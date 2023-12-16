package org.megamart.inventory.controller.item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.megamart.inventory.domain.dto.Item;
import org.megamart.inventory.domain.criteria.ItemSearchCriteria;
import org.megamart.inventory.domain.response.OPResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Item REST Controller
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Api( value = "item service", tags = { "Item" } )
@RequestMapping( "/v1/item" )
public interface IItemController
{
    @ApiOperation( value = "Return Items" )
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Item>> search( ItemSearchCriteria itemSearchCriteria );

    @ApiOperation( value = "Save Item" )
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Item>> save( @RequestBody Item item );

    @ApiOperation( value = "Delete Item" )
    @DeleteMapping( value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Void>> delete( @PathVariable long id );

    @ApiOperation( value = "Update Quantity" )
    @PatchMapping( value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Item>> updateQuantity( @PathVariable long id, @RequestBody int quantity );
}
