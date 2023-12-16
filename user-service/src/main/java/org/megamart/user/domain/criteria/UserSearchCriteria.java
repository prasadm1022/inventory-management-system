package org.megamart.user.domain.criteria;

import lombok.Getter;
import lombok.Setter;
import org.megamart.user.domain.enums.UserType;

/**
 * User Search Criteria
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class UserSearchCriteria extends AbstractSearchCriteria
{
    private long id;
    private String username;
    private UserType type;
    private String name;
    private String nic;
    private String email;
    private long phoneNo;
}
