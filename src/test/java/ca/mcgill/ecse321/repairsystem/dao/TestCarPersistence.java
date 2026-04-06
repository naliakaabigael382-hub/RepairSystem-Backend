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
public class TestCarPersistence {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired 
	private CarRepository carRepository;


	@AfterEach
	public void clearDatabase() {

		carRepository.deleteAll();
		customerRepository.deleteAll();

	}

	@Test
	public void testPersistAndLoadCar() {
		int customerId = 500;
		Customer customer = new Customer();
		customer.setId(customerId);
		customerRepository.save(customer);

		Car car = new Car();
		int carId = 501;
		car.setId(carId);
		car.setCustomer(customer);
		carRepository.save(car);

		car = null;
		car = carRepository.findByCustomer(customer).get(0);

		assertNotNull(car);
		assertEquals(carId, car.getId());
	}
}
