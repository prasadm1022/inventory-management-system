package org.megamart.inventory.service;

import org.megamart.inventory.domain.criteria.OrderSearchCriteria;
import org.megamart.inventory.domain.dto.Order;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;

/**
 * Order Service
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface IOrderService
{
    OPResponse<Order> searchOrders( OrderSearchCriteria orderSearchCriteria ) throws OPResponseError;

    OPResponse<Order> saveOrder( Order order ) throws OPResponseError;

    OPResponse<Void> deleteOrder( long id ) throws OPResponseError;
}
