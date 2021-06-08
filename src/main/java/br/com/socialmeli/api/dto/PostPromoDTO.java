package br.com.socialmeli.api.dto;

import br.com.socialmeli.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PostPromoDTO {
    private Long userId;
    @JsonIgnore
    private Date date = new Date();

    private Product detail;

    @NotNull
    private int category;

    @NotNull
    private BigDecimal price;

    @JsonIgnore
    private boolean hasPromo = true;

    private double discount;
}
