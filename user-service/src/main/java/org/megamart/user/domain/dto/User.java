package org.megamart.user.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.megamart.user.domain.enums.UserType;

/**
 * User DTO
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class User
{
    private long id;
    private String username;
    private String password;
    private UserType type;
    private String name;
    private String nic;
    private String address;
    private String email;
    private long phoneNo;
}
