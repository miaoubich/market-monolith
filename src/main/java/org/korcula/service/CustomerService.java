package org.korcula.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.korcula.dto.CustomerDto;
import org.korcula.dto.ResponseDto;
import org.korcula.model.Customer;
import org.korcula.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

		if (customer != null)
			BeanUtils.copyProperties(customer, responseDto);
		return responseDto;

	}

	public List<Customer> getAllCustomer() {
		List<Customer> customers = customerRepository.findAll();

		return customers;
	}

	public ResponseDto editCustomer(Customer customer) {
		Customer existCustomer = customerRepository.findById(customer.getId()).orElse(null);

		if (existCustomer != null) {
			existCustomer.setName(customer.getName());
			existCustomer.setEmail(customer.getEmail());
			existCustomer.setGender(customer.getGender());
			existCustomer.setProducts(customer.getProducts());

			customerRepository.save(existCustomer);
		}
		ResponseDto responseDto = new ResponseDto();
		BeanUtils.copyProperties(existCustomer, responseDto);

		return responseDto;
	}

	public String deleteCustomerById(int custId) {
		Customer existCustomer = customerRepository.findById(custId).orElse(null);
		String result = "Customer with id = " + custId + " doesn't exist!";
		if (existCustomer != null) {
			customerRepository.delete(existCustomer);
			result = "Customer deleted successfuly!";
			
			return result;
		}
		else
			throw new NoSuchElementException();
	}

	public ResponseDto updateByField(int custId, Map<Object, Object> fields) {
		Customer customer = customerRepository.findById(custId).get();

		if (customer != null) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findRequiredField(Customer.class, (String) key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, customer, value);
			});
			customerRepository.save(customer);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id = " + custId + " not found!");

		ResponseDto customerDto = new ResponseDto();
		BeanUtils.copyProperties(customer, customerDto);
		
		return customerDto;
	}
}
