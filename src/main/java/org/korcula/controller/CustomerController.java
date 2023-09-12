package org.korcula.controller;

import java.util.List;

import org.korcula.dto.CustomerDto;
import org.korcula.dto.ResponseDto;
import org.korcula.model.Customer;
import org.korcula.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/market")
public class CustomerController {

	private final CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<String> newCustomer(@RequestBody CustomerDto customer){
		String resultMessage = customerService.createNewCustomer(customer);
		return new ResponseEntity<String>(resultMessage, HttpStatus.CREATED);
	}
	
	@GetMapping("/{custId}")
	public ResponseEntity<ResponseDto> getCustomer(@PathVariable int custId){
		ResponseDto customer = customerService.getOneCustomer(custId);
		return new ResponseEntity<ResponseDto>(customer, HttpStatus.FOUND);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> printAllCustomer(){
		List<Customer> customers = customerService.getAllCustomer();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.FOUND);
	}
}
