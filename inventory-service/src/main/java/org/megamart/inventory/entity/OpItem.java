package org.megamart.inventory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Item Entity
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
@Entity
@Table( name = "OP_ITEM" )
public class OpItem implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_seq_item" )
    @SequenceGenerator( name = "id_seq_item", sequenceName = "ITEM_ID_SEQ", allocationSize = 1 )
    @Column( name = "ID" )
    private Long id;

    @Column( name = "QUANTITY" )
    private Integer quantity;

    @Column( name = "UNIT_PRICE" )
    private Double unitPrice;

    @Column( name = "CODE" )
    private String code;

    @Column( name = "NAME" )
    private String name;

    @Column( name = "BRAND" )
    private String brand;

    @Column( name = "SPECIAL_NOTES" )
    private String specialNotes;

    @OneToMany( mappedBy = "opItem", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY )
    private List<OpOrderItem> opOrderItems = new ArrayList<>();

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof OpItem ) ) return false;
        OpItem that = ( OpItem ) o;
        return Objects.equals( id, that.getId() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id );
    }
}
