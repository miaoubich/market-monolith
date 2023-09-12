package org.korcula.service;

import java.util.List;

import org.korcula.dto.CustomerDto;
import org.korcula.dto.ResponseDto;
import org.korcula.model.Customer;
import org.korcula.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	public String createNewCustomer(CustomerDto customerDto) {
		Customer newCustomer = new Customer();
		BeanUtils.copyProperties(customerDto, newCustomer);
		
		customerRepository.save(newCustomer);
		String result = "Customer successfuly added to the DB!";
		
		return result; 
	}

	public ResponseDto getOneCustomer(int custId) {
		Customer customer = customerRepository.findById(custId).get();
		ResponseDto responseDto = new ResponseDto();
		
		BeanUtils.copyProperties(customer, responseDto);
		
		return responseDto;
	}
	
	public List<Customer> getAllCustomer(){
		List<Customer> customers = customerRepository.findAll();
		
		return customers;
	}
}
