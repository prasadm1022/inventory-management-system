package org.megamart.inventory.mapper;

import org.megamart.inventory.domain.dto.Item;
import org.megamart.inventory.entity.OpItem;
import org.springframework.stereotype.Component;

/**
 * Item Mapper
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Component
public class ItemMapper
{
    public Item toDto( OpItem opItem )
    {
        Item item = new Item();

        item.setId( opItem.getId() );
        item.setQuantity( opItem.getQuantity() );
        item.setUnitPrice( opItem.getUnitPrice() );
        item.setCode( opItem.getCode() );
        item.setName( opItem.getName() );
        item.setBrand( opItem.getBrand() );
        item.setSpecialNotes( opItem.getSpecialNotes() );

        return item;
    }

    public OpItem toEntity( Item item )
    {
        OpItem opItem = new OpItem();

        opItem.setId( item.getId() );
        opItem.setId( item.getId() );
        opItem.setQuantity( item.getQuantity() );
        opItem.setUnitPrice( item.getUnitPrice() );
        opItem.setCode( item.getCode() );
        opItem.setName( item.getName() );
        opItem.setBrand( item.getBrand() );
        opItem.setSpecialNotes( item.getSpecialNotes() );

        return opItem;
    }
}
