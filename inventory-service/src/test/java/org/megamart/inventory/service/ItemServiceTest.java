package org.megamart.inventory.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.megamart.inventory.domain.criteria.ItemSearchCriteria;
import org.megamart.inventory.domain.dto.Item;
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
class ItemServiceTest
{
    @Autowired
    private IItemService itemService;

    private final ItemSearchCriteria itemSearchCriteria = new ItemSearchCriteria();
    private final Item item = new Item();

    @PostConstruct
    void initData()
    {
        item.setQuantity( 10 );
        item.setUnitPrice( 22000.00 );
        item.setCode( "STV_01" );
        item.setName( "TV" );
        item.setBrand( "Samsung" );
        item.setSpecialNotes( "LED Display" );
    }

    @Test
    @Transactional
    void testSearchItems()
    {
        // save a new item to be search
        assertDoesNotThrow( () -> itemService.saveItem( item ) );

        // test item search
        OPResponse<Item> response = assertDoesNotThrow( () -> itemService.searchItems( itemSearchCriteria ) );
        assertTrue( response != null && response.getData() != null );
        assertEquals( OPStatus.SUCCESS, response.getStatus() );
        assertFalse( response.getData().isEmpty() );
    }

    @Test
    @Transactional
    void testSaveAndDeleteItem()
    {
        // test item save
        OPResponse<Item> saveResponse = assertDoesNotThrow( () -> itemService.saveItem( item ) );
        assertTrue( saveResponse != null && saveResponse.getData() != null );
        assertEquals( OPStatus.SUCCESS, saveResponse.getStatus() );
        assertEquals( 1, saveResponse.getData().size() );

        // test item delete
        long id = saveResponse.getData().get( 0 ).getId();
        OPResponse<Void> deleteResponse = assertDoesNotThrow( () -> itemService.deleteItem( id ) );
        assertNotNull( deleteResponse );
        assertEquals( OPStatus.SUCCESS, deleteResponse.getStatus() );
    }

    @Test
    @Transactional
    void testUpdateQuantity()
    {
        final int updatedQuantity = 99;

        // save a new item to be test
        OPResponse<Item> savedItem = assertDoesNotThrow( () -> itemService.saveItem( item ) );

        // test item patch
        OPResponse<Item> updatedResponse = assertDoesNotThrow( () -> itemService.updateQuantity( savedItem.getData().get( 0 ).getId(), updatedQuantity ) );
        assertTrue( updatedResponse != null && updatedResponse.getData() != null );
        assertEquals( OPStatus.SUCCESS, updatedResponse.getStatus() );
        assertEquals( 1, updatedResponse.getData().size() );
        assertEquals( updatedQuantity, updatedResponse.getData().get( 0 ).getQuantity() );
    }
}