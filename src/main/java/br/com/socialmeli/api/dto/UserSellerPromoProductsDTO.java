package br.com.socialmeli.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSellerPromoProductsDTO {
    private long id;
    private String userName;
    private int promoProductsCount;
}
