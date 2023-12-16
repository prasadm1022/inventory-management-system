package org.megamart.inventory.service;

import org.megamart.inventory.domain.criteria.CustomerSearchCriteria;
import org.megamart.inventory.domain.dto.Customer;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;

/**
 * Customer Service
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface ICustomerService
{
    OPResponse<Customer> searchCustomers( CustomerSearchCriteria customerSearchCriteria ) throws OPResponseError;

    OPResponse<Customer> saveCustomer( Customer customer ) throws OPResponseError;

    OPResponse<Void> deleteCustomer( long id ) throws OPResponseError;
}
