package org.megamart.user.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.megamart.user.domain.criteria.UserSearchCriteria;
import org.megamart.user.domain.dto.User;
import org.megamart.user.domain.enums.UserType;
import org.megamart.user.domain.response.OPResponse;
import org.megamart.user.domain.response.OPStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.*;

@RunWith( SpringRunner.class )
@SpringBootTest
class UserServiceTest
{
    @Autowired
    private IUserService userService;

    private final UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
    private final User user = new User();

    @PostConstruct
    void initData()
    {
        user.setUsername( "john" );
        user.setPassword( "xyz" );
        user.setType( UserType.USER );
        user.setName( "John Doe" );
        user.setNic( "905678154V" );
        user.setAddress( "245/5, Maharagama" );
        user.setEmail( "abc@bc.com" );
        user.setPhoneNo( 779001013 );
    }

    @Test
    @Transactional
    void testSearchUsers()
    {
        // save a new user to be search
        assertDoesNotThrow( () -> userService.saveUser( user ) );

        // test user search
        OPResponse<User> response = assertDoesNotThrow( () -> userService.searchUsers( userSearchCriteria ) );
        assertTrue( response != null && response.getData() != null );
        assertEquals( OPStatus.SUCCESS, response.getStatus() );
        assertEquals( 1, response.getData().size() );
    }

    @Test
    @Transactional
    void testSaveAndDeleteUser()
    {
        // test user save
        OPResponse<User> saveResponse = assertDoesNotThrow( () -> userService.saveUser( user ) );
        assertTrue( saveResponse != null && saveResponse.getData() != null );
        assertEquals( OPStatus.SUCCESS, saveResponse.getStatus() );
        assertEquals( 1, saveResponse.getData().size() );

        // test user delete
        long id = saveResponse.getData().get( 0 ).getId();
        OPResponse<Void> deleteResponse = assertDoesNotThrow( () -> userService.deleteUser( id ) );
        assertNotNull( deleteResponse );
        assertEquals( OPStatus.SUCCESS, deleteResponse.getStatus() );
    }
}
