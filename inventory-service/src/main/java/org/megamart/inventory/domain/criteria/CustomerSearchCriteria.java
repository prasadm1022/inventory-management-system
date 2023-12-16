package org.megamart.inventory.domain.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer Search Criteria
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class CustomerSearchCriteria extends AbstractSearchCriteria
{
    private List<Long> ids = new ArrayList<>();
    private long phoneNo;
    private String name;
    private String email;
    private String nic;
}
