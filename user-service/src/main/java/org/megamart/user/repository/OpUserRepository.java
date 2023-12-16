package org.megamart.user.repository;

import org.megamart.user.entity.OpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * User Repository
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

public interface OpUserRepository extends JpaRepository<OpUser,Long>, JpaSpecificationExecutor<OpUser>
{
}
