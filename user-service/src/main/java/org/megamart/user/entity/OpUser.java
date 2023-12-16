package org.megamart.user.entity;

import lombok.Getter;
import lombok.Setter;
import org.megamart.user.domain.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * User Entity
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
@Entity
@Table( name = "OP_USER" )
public class OpUser implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_seq_user" )
    @SequenceGenerator( name = "id_seq_user", sequenceName = "USER_ID_SEQ", allocationSize = 1 )
    @Column( name = "ID" )
    private Long id;

    @Column( name = "USERNAME" )
    @Size( max = 128 )
    private String username;

    @Column( name = "PASSWORD" )
    @Size( max = 512 )
    private String password;

    @Enumerated( EnumType.STRING )
    @Column( name = "TYPE" )
    private UserType type;

    @Column( name = "NAME" )
    @Size( max = 128 )
    private String name;

    @Column( name = "NIC" )
    @Size( max = 16 )
    private String nic;

    @Column( name = "ADDRESS" )
    @Size( max = 128 )
    private String address;

    @Column( name = "EMAIL" )
    @Size( max = 128 )
    private String email;

    @Column( name = "PHONE_NO" )
    private Long phoneNo;

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof OpUser ) ) return false;
        OpUser that = ( OpUser ) o;
        return Objects.equals( id, that.getId() ) && Objects.equals( username, that.getUsername() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, username );
    }
}
