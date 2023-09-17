package org.korcula.repository;

import java.util.List;

import org.korcula.dto.ResponseCustomerDto;
import org.korcula.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("Select new org.korcula.dto.ResponseCustomerDto(c.customerName, p.productName) From Customer c JOIN c.products p")
	List<ResponseCustomerDto> getCustomerNameAndProductName();
}
