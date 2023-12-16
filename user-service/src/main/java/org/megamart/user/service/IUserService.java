package org.megamart.user.service;

import org.megamart.user.domain.criteria.UserSearchCriteria;
import org.megamart.user.domain.dto.User;
import org.megamart.user.domain.response.OPResponse;
import org.megamart.user.domain.response.OPResponseError;

/**
 * User Service
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface IUserService
{
    OPResponse<User> searchUsers( UserSearchCriteria userSearchCriteria ) throws OPResponseError;

    OPResponse<User> saveUser( User user ) throws OPResponseError;

    OPResponse<Void> deleteUser( long id ) throws OPResponseError;
}
