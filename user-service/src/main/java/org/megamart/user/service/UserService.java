package org.megamart.user.service;

import org.megamart.user.domain.criteria.UserSearchCriteria;
import org.megamart.user.domain.dto.User;
import org.megamart.user.domain.enums.SortDirection;
import org.megamart.user.domain.response.OPResponse;
import org.megamart.user.domain.response.OPResponseError;
import org.megamart.user.domain.response.OPStatus;
import org.megamart.user.entity.OpUser;
import org.megamart.user.mapper.UserMapper;
import org.megamart.user.repository.OpUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User Service
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Service
public class UserService implements IUserService
{
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private OpUserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public OPResponse<User> searchUsers( UserSearchCriteria userSearchCriteria ) throws OPResponseError
    {
        int page = 0;
        if( userSearchCriteria.getSize() != 0 )
        {
            page = userSearchCriteria.getStart() / userSearchCriteria.getSize();
        }
        Sort.Direction sortDirection = ( userSearchCriteria.getSortDirection() != null && userSearchCriteria.getSortDirection() == SortDirection.DESC ) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = StringUtils.hasText( userSearchCriteria.getSortBy() ) ? userSearchCriteria.getSortBy() : "id";
        userSearchCriteria.setSize( Integer.MAX_VALUE );
        PageRequest pageRequest = PageRequest.of( page, userSearchCriteria.getSize(), sortDirection, sortBy );

        try
        {
            Page<OpUser> opUsers = userRepository.findAll( ( root, criteriaQuery, criteriaBuilder ) ->
            {
                List<Predicate> predicates = new ArrayList<>();
                if( userSearchCriteria.getId() > 0 )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "id" ), userSearchCriteria.getId() ) );
                }
                if( userSearchCriteria.getUsername() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "username" ), userSearchCriteria.getUsername() ) );
                }
                if( userSearchCriteria.getName() != null )
                {
                    predicates.add( criteriaBuilder.like( criteriaBuilder.lower( root.get( "name" ) ), "%" + userSearchCriteria.getName().toLowerCase() + "%" ) );
                }
                if( userSearchCriteria.getNic() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "nic" ), userSearchCriteria.getNic() ) );
                }
                if( userSearchCriteria.getEmail() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "email" ), userSearchCriteria.getEmail() ) );
                }
                if( userSearchCriteria.getPhoneNo() > 0 )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "phoneNo" ), userSearchCriteria.getPhoneNo() ) );
                }
                criteriaQuery.distinct( true );
                return criteriaBuilder.and( predicates.toArray( new Predicate[] {} ) );
            }, pageRequest );

            List<User> response = opUsers.getContent().stream().map( opUser -> userMapper.toDto( opUser ) ).collect( Collectors.toList() );
            return new OPResponse<>( response, response.size() );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<User> saveUser( User user ) throws OPResponseError
    {
        try
        {
            user.setPassword( passwordEncoder.encode( user.getPassword() ) );
            OpUser savedUser = userRepository.save( userMapper.toEntity( user ) );
            return new OPResponse<>( userMapper.toDto( savedUser ) );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<Void> deleteUser( long id ) throws OPResponseError
    {
        try
        {
            if( !userRepository.existsById( id ) )
            {
                return new OPResponse<>( OPStatus.ERROR );
            }
            userRepository.deleteById( id );
            return new OPResponse<>( OPStatus.SUCCESS );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }
}
