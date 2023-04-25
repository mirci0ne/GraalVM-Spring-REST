package org.check.something.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponse {

    private String productName;
    private int stock;
}
