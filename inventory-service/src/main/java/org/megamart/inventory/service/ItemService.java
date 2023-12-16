package org.megamart.inventory.service;

import org.megamart.inventory.domain.criteria.ItemSearchCriteria;
import org.megamart.inventory.domain.dto.Item;
import org.megamart.inventory.domain.enums.SortDirection;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;
import org.megamart.inventory.domain.response.OPStatus;
import org.megamart.inventory.entity.OpItem;
import org.megamart.inventory.mapper.ItemMapper;
import org.megamart.inventory.repository.OpItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Item Service
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Service
public class ItemService implements IItemService
{
    @Autowired
    private OpItemRepository inventoryRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public OPResponse<Item> searchItems( ItemSearchCriteria itemSearchCriteria ) throws OPResponseError
    {
        int page = 0;
        if( itemSearchCriteria.getSize() != 0 )
        {
            page = itemSearchCriteria.getStart() / itemSearchCriteria.getSize();
        }
        Sort.Direction sortDirection = ( itemSearchCriteria.getSortDirection() != null && itemSearchCriteria.getSortDirection() == SortDirection.DESC ) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = StringUtils.hasText( itemSearchCriteria.getSortBy() ) ? itemSearchCriteria.getSortBy() : "id";
        itemSearchCriteria.setSize( Integer.MAX_VALUE );
        PageRequest pageRequest = PageRequest.of( page, itemSearchCriteria.getSize(), sortDirection, sortBy );

        try
        {
            Page<OpItem> opItems = inventoryRepository.findAll( ( root, criteriaQuery, criteriaBuilder ) ->
            {
                List<Predicate> predicates = new ArrayList<>();
                if( itemSearchCriteria.getIds() != null && !itemSearchCriteria.getIds().isEmpty() )
                {
                    predicates.add( criteriaBuilder.and( root.get( "id" ).in( itemSearchCriteria.getIds() ) ) );
                }
                if( itemSearchCriteria.getCode() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "code" ), itemSearchCriteria.getCode() ) );
                }
                if( itemSearchCriteria.getName() != null )
                {
                    predicates.add( criteriaBuilder.like( criteriaBuilder.lower( root.get( "name" ) ), "%" + itemSearchCriteria.getName().toLowerCase() + "%" ) );
                }
                if( itemSearchCriteria.getBrand() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "brand" ), itemSearchCriteria.getBrand() ) );
                }
                criteriaQuery.distinct( true );
                return criteriaBuilder.and( predicates.toArray( new Predicate[] {} ) );
            }, pageRequest );

            List<Item> response = opItems.getContent().stream().map( opItem -> itemMapper.toDto( opItem ) ).collect( Collectors.toList() );
            return new OPResponse<>( response, response.size() );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<Item> saveItem( Item item ) throws OPResponseError
    {
        try
        {
            OpItem savedItem = inventoryRepository.save( itemMapper.toEntity( item ) );
            return new OPResponse<>( itemMapper.toDto( savedItem ) );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<Void> deleteItem( long id ) throws OPResponseError
    {
        try
        {
            if( !inventoryRepository.existsById( id ) )
            {
                return new OPResponse<>( OPStatus.ERROR );
            }
            inventoryRepository.deleteById( id );
            return new OPResponse<>( OPStatus.SUCCESS );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<Item> updateQuantity( long id, int quantity ) throws OPResponseError
    {
        try
        {
            Optional<OpItem> optionalOpItem = inventoryRepository.findById( id );
            if( optionalOpItem.isEmpty() )
            {
                return new OPResponse<>( OPStatus.ERROR );
            }
            optionalOpItem.get().setQuantity( quantity );
            OpItem updatedResponse = inventoryRepository.save( optionalOpItem.get() );
            return new OPResponse<>( itemMapper.toDto( updatedResponse ) );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }
}
