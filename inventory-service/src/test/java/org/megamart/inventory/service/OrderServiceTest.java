package org.megamart.inventory.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.megamart.inventory.domain.criteria.OrderSearchCriteria;
import org.megamart.inventory.domain.dto.Customer;
import org.megamart.inventory.domain.dto.Item;
import org.megamart.inventory.domain.dto.Order;
import org.megamart.inventory.domain.enums.OrderStatus;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPStatus;
import org.megamart.inventory.entity.OpCustomer;
import org.megamart.inventory.entity.OpItem;
import org.megamart.inventory.mapper.CustomerMapper;
import org.megamart.inventory.mapper.ItemMapper;
import org.megamart.inventory.repository.OpCustomerRepository;
import org.megamart.inventory.repository.OpItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith( SpringRunner.class )
@SpringBootTest
class OrderServiceTest
{
    @Autowired
    private IOrderService orderService;
    @Autowired
    private OpCustomerRepository customerRepository;
    @Autowired
    private OpItemRepository itemRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ItemMapper itemMapper;

    private final OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria();
    private final Customer customer = new Customer();
    private final Order order = new Order();
    private final Item item1 = new Item();
    private final Item item2 = new Item();

    @PostConstruct
    void initData()
    {
        item1.setQuantity( 5 );
        item1.setUnitPrice( 1500.00 );
        item1.setCode( "TV_01" );
        item1.setName( "TV" );
        item1.setBrand( "Samsung" );
        item1.setSpecialNotes( "LED Display" );

        item2.setQuantity( 3 );
        item2.setUnitPrice( 750.00 );
        item2.setCode( "JBL_01" );
        item2.setName( "JBL" );
        item2.setBrand( "JBL" );
        item2.setSpecialNotes( "Waterproof" );

        customer.setPhoneNo( 779001013 );
        customer.setName( "John Doe" );
        customer.setAddress( "245/5, Maharagama" );
        customer.setEmail( "abc@bc.com" );
        customer.setNic( "905678154V" );

        order.setTotalPrice( 44000.00 );
        order.setDeliveryArea( "Colombo" );
        order.setDeliveryService( "DHL" );
        order.setDispatchDate( new Date() );
        order.setStatus( OrderStatus.PENDING );

        // save a new customer
        OpCustomer opCustomer = assertDoesNotThrow( () -> customerRepository.save( customerMapper.toEntity( customer ) ) );
        order.setCustomerId( opCustomer.getId() );

        // save new items
        List<OpItem> opItems = assertDoesNotThrow( () -> itemRepository.saveAll( Arrays.asList( itemMapper.toEntity( item1 ), itemMapper.toEntity( item2 ) ) ) );
        opItems.forEach( opItem -> order.getItems().put( opItem.getId(), 1 ) );
    }

    @Test
    @Transactional
    void testSearchOrders()
    {
        // save a new order to be search
        assertDoesNotThrow( () -> orderService.saveOrder( order ) );

        // test order search
        OPResponse<Order> response = assertDoesNotThrow( () -> orderService.searchOrders( orderSearchCriteria ) );
        assertTrue( response != null && response.getData() != null );
        assertEquals( OPStatus.SUCCESS, response.getStatus() );
        assertEquals( 1, response.getData().size() );
    }

    @Test
    @Transactional
    void testSaveAndDeleteOrder()
    {
        // test order save
        OPResponse<Order> saveResponse = assertDoesNotThrow( () -> orderService.saveOrder( order ) );
        assertTrue( saveResponse != null && saveResponse.getData() != null );
        assertEquals( OPStatus.SUCCESS, saveResponse.getStatus() );
        assertEquals( 1, saveResponse.getData().size() );

        // test order delete
        long id = saveResponse.getData().get( 0 ).getId();
        OPResponse<Void> deleteResponse = assertDoesNotThrow( () -> orderService.deleteOrder( id ) );
        assertNotNull( deleteResponse );
        assertEquals( OPStatus.SUCCESS, deleteResponse.getStatus() );
    }
}