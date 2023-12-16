package org.megamart.inventory.domain.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Item Search Criteria
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class ItemSearchCriteria extends AbstractSearchCriteria
{
    private List<Long> ids = new ArrayList<>();
    private String code;
    private String name;
    private String brand;
}
