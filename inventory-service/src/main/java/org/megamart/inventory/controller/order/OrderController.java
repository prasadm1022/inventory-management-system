package org.megamart.inventory.controller.order;

import org.megamart.inventory.domain.criteria.OrderSearchCriteria;
import org.megamart.inventory.domain.dto.Order;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;
import org.megamart.inventory.domain.response.OPResponseWrapper;
import org.megamart.inventory.service.IOrderService;
import org.megamart.inventory.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Order REST Controller
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@RestController
public class OrderController implements IOrderController
{
    @Autowired
    private IOrderService orderService;

    @Override
    public ResponseEntity<OPResponseWrapper<Order>> search( OrderSearchCriteria orderSearchCriteria )
    {
        try
        {
            OPResponse<Order> opResponse = orderService.searchOrders( orderSearchCriteria );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<OPResponseWrapper<Order>> save( Order order )
    {
        try
        {
            OPResponse<Order> opResponse = orderService.saveOrder( order );
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
            OPResponse<Void> opResponse = orderService.deleteOrder( id );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }
}
