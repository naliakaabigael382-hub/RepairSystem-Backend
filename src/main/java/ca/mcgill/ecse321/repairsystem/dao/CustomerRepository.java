package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.*;
import java.util.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customer_data", path = "customer_data" )
public interface CustomerRepository extends CrudRepository<Customer, String>{
	
	Customer findById(int Id);
	List<Customer> findByName(String name);
	Customer findByPhone(long number);
	Customer findByEmail(String email);
	List<Customer> findByAddress(String address);
	List<Customer> findAll();
	
}