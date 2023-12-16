package org.megamart.inventory.service;

import org.megamart.inventory.domain.criteria.OrderSearchCriteria;
import org.megamart.inventory.domain.dto.Order;
import org.megamart.inventory.domain.enums.SortDirection;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;
import org.megamart.inventory.domain.response.OPStatus;
import org.megamart.inventory.entity.OpOrder;
import org.megamart.inventory.mapper.OrderMapper;
import org.megamart.inventory.repository.OpOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Order Service
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Service
public class OrderService implements IOrderService
{
    @Autowired
    private OpOrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OPResponse<Order> searchOrders( OrderSearchCriteria orderSearchCriteria ) throws OPResponseError
    {
        int page = 0;
        if( orderSearchCriteria.getSize() != 0 )
        {
            page = orderSearchCriteria.getStart() / orderSearchCriteria.getSize();
        }
        Sort.Direction sortDirection = ( orderSearchCriteria.getSortDirection() != null && orderSearchCriteria.getSortDirection() == SortDirection.DESC ) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = StringUtils.hasText( orderSearchCriteria.getSortBy() ) ? orderSearchCriteria.getSortBy() : "id";
        orderSearchCriteria.setSize( Integer.MAX_VALUE );
        PageRequest pageRequest = PageRequest.of( page, orderSearchCriteria.getSize(), sortDirection, sortBy );

        try
        {
            Page<OpOrder> opOrders = orderRepository.findAll( ( root, criteriaQuery, criteriaBuilder ) ->
            {
                List<Predicate> predicates = new ArrayList<>();
                if( orderSearchCriteria.getIds() != null && !orderSearchCriteria.getIds().isEmpty() )
                {
                    predicates.add( criteriaBuilder.and( root.get( "id" ).in( orderSearchCriteria.getIds() ) ) );
                }
                if( orderSearchCriteria.getDeliveryService() != null )
                {
                    predicates.add( criteriaBuilder.like( criteriaBuilder.lower( root.get( "deliveryService" ) ), "%" + orderSearchCriteria.getDeliveryService().toLowerCase() + "%" ) );
                }
                if( orderSearchCriteria.getDeliveryArea() != null )
                {
                    predicates.add( criteriaBuilder.like( criteriaBuilder.lower( root.get( "deliveryArea" ) ), "%" + orderSearchCriteria.getDeliveryArea().toLowerCase() + "%" ) );
                }
                if( orderSearchCriteria.getDispatchDate() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "dispatchDate" ), orderSearchCriteria.getDispatchDate() ) );
                }
                if( orderSearchCriteria.getStatus() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "status" ), orderSearchCriteria.getStatus() ) );
                }
                criteriaQuery.distinct( true );
                return criteriaBuilder.and( predicates.toArray( new Predicate[] {} ) );
            }, pageRequest );

            List<Order> response = opOrders.getContent().stream().map( opOrder -> orderMapper.toDto( opOrder ) ).collect( Collectors.toList() );
            return new OPResponse<>( response, response.size() );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<Order> saveOrder( Order order ) throws OPResponseError
    {
        try
        {
            OpOrder opOrder;
            if( order.getId() > 0 )
            {
                opOrder = orderMapper.toUpdatedEntity( order );
            }
            else
            {
                opOrder = orderMapper.toNewEntity( order );
                opOrder.getOpCustomer().getOpOrders().add( opOrder );
            }
            OpOrder savedResponse = orderRepository.save( opOrder );
            return new OPResponse<>( orderMapper.toDto( savedResponse ) );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<Void> deleteOrder( long id ) throws OPResponseError
    {
        try
        {
            if( !orderRepository.existsById( id ) )
            {
                return new OPResponse<>( OPStatus.ERROR );
            }
            orderRepository.deleteById( id );
            return new OPResponse<>( OPStatus.SUCCESS );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }
}
