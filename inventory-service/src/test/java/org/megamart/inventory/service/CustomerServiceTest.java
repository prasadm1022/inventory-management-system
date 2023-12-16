package org.megamart.inventory.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.megamart.inventory.domain.criteria.CustomerSearchCriteria;
import org.megamart.inventory.domain.dto.Customer;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;

@RunWith( SpringRunner.class )
@SpringBootTest
class CustomerServiceTest
{
    @Autowired
    private ICustomerService customerService;

    private final CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria();
    private final Customer customer = new Customer();

    @PostConstruct
    void initData()
    {
        customer.setPhoneNo( 779001013 );
        customer.setName( "John Doe" );
        customer.setAddress( "245/5, Maharagama" );
        customer.setEmail( "abc@bc.com" );
        customer.setNic( "905678154V" );
    }

    @Test
    @Transactional
    void testSearchCustomers()
    {
        // save a new customer to be search
        assertDoesNotThrow( () -> customerService.saveCustomer( customer ) );

        // test customer search
        OPResponse<Customer> response = assertDoesNotThrow( () -> customerService.searchCustomers( customerSearchCriteria ) );
        assertTrue( response != null && response.getData() != null );
        assertEquals( OPStatus.SUCCESS, response.getStatus() );
        assertEquals( 1, response.getData().size() );
    }

    @Test
    @Transactional
    void testSaveAndDeleteCustomer()
    {
        // test customer save
        OPResponse<Customer> saveResponse = assertDoesNotThrow( () -> customerService.saveCustomer( customer ) );
        assertTrue( saveResponse != null && saveResponse.getData() != null );
        assertEquals( OPStatus.SUCCESS, saveResponse.getStatus() );
        assertEquals( 1, saveResponse.getData().size() );

        // test customer delete
        long id = saveResponse.getData().get( 0 ).getId();
        OPResponse<Void> deleteResponse = assertDoesNotThrow( () -> customerService.deleteCustomer( id ) );
        assertNotNull( deleteResponse );
        assertEquals( OPStatus.SUCCESS, deleteResponse.getStatus() );
    }
}