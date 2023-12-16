package org.megamart.inventory.repository;

import org.megamart.inventory.entity.OpOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Order Item Repository
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface OpOrderItemRepository extends JpaRepository<OpOrderItem,Long>, JpaSpecificationExecutor<OpOrderItem>
{
}
