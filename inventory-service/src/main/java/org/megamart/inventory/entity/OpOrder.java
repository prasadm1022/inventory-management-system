package org.megamart.inventory.entity;

import lombok.Getter;
import lombok.Setter;
import org.megamart.inventory.domain.enums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Order Entity
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
@Entity
@Table( name = "OP_ORDER" )
public class OpOrder implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_seq_order" )
    @SequenceGenerator( name = "id_seq_order", sequenceName = "ORDER_ID_SEQ", allocationSize = 1 )
    @Column( name = "ID" )
    private Long id;

    @Column( name = "TOTAL_PRICE" )
    private Double totalPrice;

    @Column( name = "DELIVERY_AREA" )
    @Size( max = 64 )
    private String deliveryArea;

    @Column( name = "DELIVERY_SERVICE" )
    @Size( max = 128 )
    private String deliveryService;

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "DISPATCH_DATE" )
    private Date dispatchDate;

    @Enumerated( EnumType.STRING )
    @Column( name = "STATUS" )
    private OrderStatus status;

    @ManyToOne( cascade = { CascadeType.ALL }, fetch = FetchType.LAZY )
    @JoinColumn( name = "CUSTOMER_ID", referencedColumnName = "ID" )
    private OpCustomer opCustomer;

    @OneToMany( mappedBy = "opOrder", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY )
    private List<OpOrderItem> opOrderItems = new ArrayList<>();

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) return true;
        if( !( o instanceof OpOrder ) ) return false;
        OpOrder that = ( OpOrder ) o;
        return Objects.equals( id, that.getId() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id );
    }
}
