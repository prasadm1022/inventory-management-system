package org.megamart.user.mapper;

import org.megamart.user.domain.dto.User;
import org.megamart.user.entity.OpUser;
import org.springframework.stereotype.Component;

/**
 * User Mapper
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Component
public class UserMapper
{
    public User toDto( OpUser opUser )
    {
        User user = new User();

        user.setId( opUser.getId() );
        user.setUsername( opUser.getUsername() );
        user.setType( opUser.getType() );
        user.setName( opUser.getName() );
        user.setNic( opUser.getNic() );
        user.setAddress( opUser.getAddress() );
        user.setEmail( opUser.getEmail() );
        user.setPhoneNo( opUser.getPhoneNo() );

        return user;
    }

    public OpUser toEntity( User user )
    {
        OpUser opUser = new OpUser();

        opUser.setId( user.getId() );
        opUser.setUsername( user.getUsername() );
        opUser.setPassword( user.getPassword() );
        opUser.setType( user.getType() );
        opUser.setName( user.getName() );
        opUser.setNic( user.getNic() );
        opUser.setAddress( user.getAddress() );
        opUser.setEmail( user.getEmail() );
        opUser.setPhoneNo( user.getPhoneNo() );

        return opUser;
    }
}
