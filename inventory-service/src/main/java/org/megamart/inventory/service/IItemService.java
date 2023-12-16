package org.megamart.inventory.service;

import org.megamart.inventory.domain.dto.Item;
import org.megamart.inventory.domain.criteria.ItemSearchCriteria;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;

/**
 * Item Service
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface IItemService
{
    OPResponse<Item> searchItems( ItemSearchCriteria itemSearchCriteria ) throws OPResponseError;

    OPResponse<Item> saveItem( Item item ) throws OPResponseError;

    OPResponse<Void> deleteItem( long id ) throws OPResponseError;

    OPResponse<Item> updateQuantity( long id, int quantity ) throws OPResponseError;
}
