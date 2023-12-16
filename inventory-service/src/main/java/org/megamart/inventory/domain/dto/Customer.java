package org.megamart.inventory.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Customer DTO
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class Customer
{
    private long id;
    private long phoneNo;
    private String name;
    private String address;
    private String email;
    private String nic;
    private List<Long> orderIdList = new ArrayList<>();

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof Customer ) ) return false;
        Customer that = ( Customer ) o;
        return Objects.equals( id, that.getId() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id );
    }
}
