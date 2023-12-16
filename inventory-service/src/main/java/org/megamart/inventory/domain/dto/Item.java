package org.megamart.inventory.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Item DTO
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class Item
{
    private long id;
    private int quantity;
    private double unitPrice;
    private String code;
    private String name;
    private String brand;
    private String specialNotes;

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof Item ) ) return false;
        Item that = ( Item ) o;
        return Objects.equals( id, that.getId() )
                       && Objects.equals( code, that.getCode() )
                       && Objects.equals( name, that.getName() )
                       && Objects.equals( brand, that.getBrand() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, code, name, brand );
    }
}
