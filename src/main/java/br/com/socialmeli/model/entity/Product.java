package br.com.socialmeli.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRODUCTS")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    private String productName;

    @NotNull
    private String type;

    @NotNull
    private String brand;

    @NotNull
    private String color;

    @NotNull
    private String notes;

}
