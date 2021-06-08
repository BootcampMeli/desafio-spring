package br.com.socialmeli.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_POSTS")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private User user;

	private Date date;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product detail;

	@NotNull
	private int category;

	@NotNull
	private BigDecimal price;

	private boolean hasPromo = false;

	private double discount = 0.0;

	public void setDiscount(double discount){
		if (hasPromo) this.discount = discount;
	}

}
