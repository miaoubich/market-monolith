package org.korcula.dto;

import java.util.List;

import org.korcula.model.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	@Id
	@GeneratedValue
	private int id;
	private String customerName;
	private String email;
	private String gender;
	@OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
	private List<Product> products;
}
