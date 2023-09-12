package org.korcula.dto;

import java.util.List;

import org.korcula.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	private String name;
	private String email;
	private String gender;
	private List<Product> products;
}
