package org.megamart.user.controller.user;

import org.megamart.user.domain.criteria.UserSearchCriteria;
import org.megamart.user.domain.dto.User;
import org.megamart.user.domain.response.OPResponse;
import org.megamart.user.domain.response.OPResponseError;
import org.megamart.user.domain.response.OPResponseWrapper;
import org.megamart.user.service.IUserService;
import org.megamart.user.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * User REST Controller
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@RestController
public class UserController implements IUserController
{
    @Autowired
    private IUserService userService;

    @Override
    public ResponseEntity<OPResponseWrapper<User>> search( UserSearchCriteria userSearchCriteria )
    {
        try
        {
            OPResponse<User> opResponse = userService.searchUsers( userSearchCriteria );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }

    @Override
    public ResponseEntity<OPResponseWrapper<User>> save( User user )
    {
        try
        {
            OPResponse<User> opResponse = userService.saveUser( user );
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
            OPResponse<Void> opResponse = userService.deleteUser( id );
            return ResponseUtil.wrap( opResponse );
        }
        catch( OPResponseError ex )
        {
            return ResponseUtil.wrap( ex );
        }
    }
}
