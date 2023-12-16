package org.megamart.inventory.controller.customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.megamart.inventory.domain.criteria.CustomerSearchCriteria;
import org.megamart.inventory.domain.dto.Customer;
import org.megamart.inventory.domain.response.OPResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Customer REST Controller
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Api( value = "customer service", tags = { "Customer" } )
@RequestMapping( "/v1/customer" )
public interface ICustomerController
{
    @ApiOperation( value = "Return Customers" )
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Customer>> search( CustomerSearchCriteria customerSearchCriteria );

    @ApiOperation( value = "Save Customer" )
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Customer>> save( @RequestBody Customer customer );

    @ApiOperation( value = "Delete Customer" )
    @DeleteMapping( value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Void>> delete( @PathVariable long id );
}
