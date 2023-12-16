package org.megamart.inventory.repository;

import org.megamart.inventory.entity.OpCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Customer Repository
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface OpCustomerRepository extends JpaRepository<OpCustomer,Long>, JpaSpecificationExecutor<OpCustomer>
{
}
