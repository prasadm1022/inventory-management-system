package org.megamart.inventory.domain.criteria;

import lombok.Getter;
import lombok.Setter;
import org.megamart.inventory.domain.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Order Search Criteria
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class OrderSearchCriteria extends AbstractSearchCriteria
{
    private List<Long> ids = new ArrayList<>();
    private String deliveryArea;
    private String deliveryService;
    private Date dispatchDate;
    private OrderStatus status;
}
