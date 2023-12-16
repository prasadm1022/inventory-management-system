package org.megamart.user.domain.response;

import lombok.*;

/**
 * Page Data
 *
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageData
{
    private int count;
    private int total;
}
