package org.megamart.inventory.service;

import org.megamart.inventory.domain.criteria.CustomerSearchCriteria;
import org.megamart.inventory.domain.dto.Customer;
import org.megamart.inventory.domain.enums.SortDirection;
import org.megamart.inventory.domain.response.OPResponse;
import org.megamart.inventory.domain.response.OPResponseError;
import org.megamart.inventory.domain.response.OPStatus;
import org.megamart.inventory.entity.OpCustomer;
import org.megamart.inventory.mapper.CustomerMapper;
import org.megamart.inventory.repository.OpCustomerRepository;
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
 * Customer Service
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Service
public class CustomerService implements ICustomerService
{
    @Autowired
    private OpCustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public OPResponse<Customer> searchCustomers( CustomerSearchCriteria customerSearchCriteria ) throws OPResponseError
    {
        int page = 0;
        if( customerSearchCriteria.getSize() != 0 )
        {
            page = customerSearchCriteria.getStart() / customerSearchCriteria.getSize();
        }
        Sort.Direction sortDirection = ( customerSearchCriteria.getSortDirection() != null && customerSearchCriteria.getSortDirection() == SortDirection.DESC ) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = StringUtils.hasText( customerSearchCriteria.getSortBy() ) ? customerSearchCriteria.getSortBy() : "id";
        customerSearchCriteria.setSize( Integer.MAX_VALUE );
        PageRequest pageRequest = PageRequest.of( page, customerSearchCriteria.getSize(), sortDirection, sortBy );

        try
        {
            Page<OpCustomer> opCustomers = customerRepository.findAll( ( root, criteriaQuery, criteriaBuilder ) ->
            {
                List<Predicate> predicates = new ArrayList<>();
                if( customerSearchCriteria.getIds() != null && !customerSearchCriteria.getIds().isEmpty() )
                {
                    predicates.add( criteriaBuilder.and( root.get( "id" ).in( customerSearchCriteria.getIds() ) ) );
                }
                if( customerSearchCriteria.getPhoneNo() > 0 )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "phoneNo" ), customerSearchCriteria.getPhoneNo() ) );
                }
                if( customerSearchCriteria.getName() != null )
                {
                    predicates.add( criteriaBuilder.like( criteriaBuilder.lower( root.get( "name" ) ), "%" + customerSearchCriteria.getName().toLowerCase() + "%" ) );
                }
                if( customerSearchCriteria.getEmail() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "email" ), customerSearchCriteria.getEmail() ) );
                }
                if( customerSearchCriteria.getNic() != null )
                {
                    predicates.add( criteriaBuilder.equal( root.get( "nic" ), customerSearchCriteria.getNic() ) );
                }
                criteriaQuery.distinct( true );
                return criteriaBuilder.and( predicates.toArray( new Predicate[] {} ) );
            }, pageRequest );

            List<Customer> response = opCustomers.getContent().stream().map( opCustomer -> customerMapper.toDto( opCustomer ) ).collect( Collectors.toList() );
            return new OPResponse<>( response, response.size() );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<Customer> saveCustomer( Customer customer ) throws OPResponseError
    {
        try
        {
            OpCustomer savedCustomer = customerRepository.save( customerMapper.toEntity( customer ) );
            return new OPResponse<>( customerMapper.toDto( savedCustomer ) );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }

    @Override
    public OPResponse<Void> deleteCustomer( long id ) throws OPResponseError
    {
        try
        {
            if( !customerRepository.existsById( id ) )
            {
                return new OPResponse<>( OPStatus.ERROR );
            }
            customerRepository.deleteById( id );
            return new OPResponse<>( OPStatus.SUCCESS );
        }
        catch( Exception ex )
        {
            throw new OPResponseError( ex );
        }
    }
}
