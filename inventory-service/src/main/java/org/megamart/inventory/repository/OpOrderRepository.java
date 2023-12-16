package org.megamart.inventory.repository;

import org.megamart.inventory.entity.OpOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Order Repository
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface OpOrderRepository extends JpaRepository<OpOrder,Long>, JpaSpecificationExecutor<OpOrder>
{
}
