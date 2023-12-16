package org.megamart.user.domain.criteria;

import lombok.Getter;
import lombok.Setter;
import org.megamart.user.domain.enums.SortDirection;

/**
 * Abstract Search Criteria
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class AbstractSearchCriteria
{
    private int start;
    private int page;
    private int size;
    private String sortBy;
    private SortDirection sortDirection;

    public AbstractSearchCriteria()
    {
        this.size = 10;
        this.page = 0;
        this.start = 0;
    }

    protected AbstractSearchCriteria( int start, int page, int size, String sortBy, SortDirection sortDirection )
    {
        this.start = start;
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }
}
