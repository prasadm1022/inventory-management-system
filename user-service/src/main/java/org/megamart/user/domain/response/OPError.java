package org.megamart.user.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prasadm
 * @since 23 Feb 2021
 */

@Getter
@Setter
public class OPError
{
    private long code;
    private String message;
    private String details;
    private List<String> errors;

    public OPError()
    {
        errors = new ArrayList<>();
    }
}
