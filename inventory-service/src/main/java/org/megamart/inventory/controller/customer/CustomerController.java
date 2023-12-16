package org.megamart.inventory.controller.customer;

import org.megamart.inventory.domain.criteria.CustomerSearchCriteria;
import org.megamart.inventory.domain.dto.Customer;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;
import org.megamart.inventory.domain.response.OPResponseWrapper;
import org.megamart.inventory.service.ICustomerService;
import org.megamart.inventory.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Customer REST Controller
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@RestController
public class CustomerController implements ICustomerController
{
    @Autowired
    private ICustomerService customerService;

    @Override
    public ResponseEntity<OPResponseWrapper<Customer>> search( CustomerSearchCriteria customerSearchCriteria )
    {
        try
        {
            OPResponse<Customer> opResponse = customerService.searchCustomers( customerSearchCriteria );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<OPResponseWrapper<Customer>> save( Customer customer )
    {
        try
        {
            OPResponse<Customer> opResponse = customerService.saveCustomer( customer );
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
            OPResponse<Void> opResponse = customerService.deleteCustomer( id );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }
}
