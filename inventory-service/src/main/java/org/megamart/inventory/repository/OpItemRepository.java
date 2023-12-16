package org.megamart.inventory.repository;

import org.megamart.inventory.entity.OpItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Item Repository
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface OpItemRepository extends JpaRepository<OpItem,Long>, JpaSpecificationExecutor<OpItem>
{
}
