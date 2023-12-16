package org.megamart.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Customer Entity
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
@Entity
@Table( name = "OP_CUSTOMER" )
public class OpCustomer implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_seq_customer" )
    @SequenceGenerator( name = "id_seq_customer", sequenceName = "CUSTOMER_ID_SEQ", allocationSize = 1 )
    @Column( name = "ID" )
    private Long id;

    @Column( name = "PHONE_NO" )
    private Long phoneNo;

    @Column( name = "NAME" )
    @Size( max = 128 )
    private String name;

    @Column( name = "ADDRESS" )
    @Size( max = 128 )
    private String address;

    @Column( name = "EMAIL" )
    @Size( max = 128 )
    private String email;

    @Column( name = "NIC" )
    @Size( max = 16 )
    private String nic;

    @OneToMany( mappedBy = "opCustomer", fetch = FetchType.LAZY )
    private List<OpOrder> opOrders = new ArrayList<>();

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof OpCustomer ) ) return false;
        OpCustomer that = ( OpCustomer ) o;
        return Objects.equals( id, that.getId() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id );
    }
}
