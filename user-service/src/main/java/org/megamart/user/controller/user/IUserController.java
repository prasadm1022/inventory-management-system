package org.megamart.user.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.megamart.user.domain.criteria.UserSearchCriteria;
import org.megamart.user.domain.dto.User;
import org.megamart.user.domain.response.OPResponseWrapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User REST Controller
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Api( value = "user service", tags = { "User" } )
@RequestMapping( "/v1/user" )
public interface IUserController
{
    @ApiOperation( value = "Return Users" )
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<User>> search( UserSearchCriteria userSearchCriteria );

    @ApiOperation( value = "Save User" )
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<User>> save( @RequestBody User user );

    @ApiOperation( value = "Delete User" )
    @DeleteMapping( value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<OPResponseWrapper<Void>> delete( @PathVariable long id );
}
