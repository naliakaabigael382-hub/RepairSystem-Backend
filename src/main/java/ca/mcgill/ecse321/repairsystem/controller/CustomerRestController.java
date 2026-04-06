package ca.mcgill.ecse321.repairsystem.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.repairsystem.dto.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	/**
	 *restful controller fot getting all customers
	 * */
	@GetMapping(value = { "/customer", "/customer/"})
	public List<CustomerDto> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
		for(Customer customer: customers) {
			customersDto.add(Converter.convertToDto(customer));
		}
		return customersDto;
	}
	/**
	 *restful controller for getting customer by id
	 * */
	@GetMapping(value = { "/customer/{id}", "/customer/{id}/"})
	public CustomerDto getCustomerById(@PathVariable("id") String id) {
		return Converter.convertToDto(customerService.getCustomerById(Integer.parseInt(id)));
	}
	
	/**
	 *restful controller for getting customer by id
	 * */
	@GetMapping(value = { "/customer/login/{email}", "/customer/login/{email}/"})
	public CustomerDto getCustomerFromLogin(@PathVariable("email") String email, @RequestParam String password) {
		Customer c = customerService.getCustomerByEmail(email);
		if(c != null && password.equals(c.getPassword())) {
			return Converter.convertToDto(c);
		}
		return null;
	}
	
	/**
	 *restful controller for creating customer by
	 * */
	@PostMapping(value = { "/customer/{name}", "/customer/{name}/" })
	public CustomerDto createCustomer(@PathVariable("name") String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String credit, @RequestParam String debit, @RequestParam String address) throws IllegalArgumentException, ParseException {
		Customer customer = customerService.createCustomer(name, password, Long.parseLong(phone), email, credit, debit, address);
		return Converter.convertToDto(customer);
	}
	/**
	 *restful controller for editing all customer credentials
	 * */
	@PutMapping(value = { "/customer/{oldEmail}", "/customer/{oldEmail}/" })
	public CustomerDto editCustomer(@PathVariable("oldEmail") String oldEmail, @RequestParam String newName, @RequestParam String newPassword, @RequestParam String newPhone,  @RequestParam String newCredit, @RequestParam String newDebit, @RequestParam String newAddress) throws IllegalArgumentException {
		Customer customer = customerService.updateAllCredentials(oldEmail, newName, newPassword, newPhone, newCredit, newDebit, newAddress);
		return Converter.convertToDto(customer);
	}

	/**
	 * Restful controller for deleting a customer from the database
	 */
	@DeleteMapping(value= {"/customer/{id}", "customer/{id}/"})
	public boolean removeCustomer(@PathVariable("id") String id) {
		customerService.deleteCustomer(Integer.parseInt(id));
		return true;
	}
}




