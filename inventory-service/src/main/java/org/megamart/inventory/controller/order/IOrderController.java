package org.megamart.inventory.controller.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.megamart.inventory.domain.criteria.OrderSearchCriteria;
import org.megamart.inventory.domain.dto.Order;
import org.megamart.inventory.domain.response.OPResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Order REST Controller
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Api( value = "order service", tags = { "Order" } )
@RequestMapping( "/v1/order" )
public interface IOrderController
{
    @ApiOperation( value = "Return Orders" )
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Order>> search( OrderSearchCriteria orderSearchCriteria );

    @ApiOperation( value = "Save Order" )
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Order>> save( @RequestBody Order order );

    @ApiOperation( value = "Delete Order" )
    @DeleteMapping( value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Void>> delete( @PathVariable long id );
}
