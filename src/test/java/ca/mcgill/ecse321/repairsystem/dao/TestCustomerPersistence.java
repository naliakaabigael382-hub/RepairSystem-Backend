package ca.mcgill.ecse321.repairsystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.repairsystem.RepairSystemApplication;
import ca.mcgill.ecse321.repairsystem.model.*;

@ExtendWith(SpringExtension.class)

@SpringBootTest(classes = RepairSystemApplication.class)
public class TestCustomerPersistence {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	@AfterEach
	public void clearDatabase() {
		customerRepository.deleteAll();
	}
	
	
	@Test
	public void testPersistAndLoadCustomer() {
		Customer customer = new Customer();
		int Id = 200;
		customer.setId(Id);
		customerRepository.save(customer);
		
		customer = null;
		customer = customerRepository.findById(Id);
		
		assertNotNull(customer);
		assertEquals(Id, customer.getId());
		
	}
	
}
