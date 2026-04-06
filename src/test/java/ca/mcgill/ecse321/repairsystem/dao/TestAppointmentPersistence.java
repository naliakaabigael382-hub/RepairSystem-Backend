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

public class TestAppointmentPersistence {
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@AfterEach
	public void clearDatabase() {
		appointmentRepository.deleteAll();
		customerRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadAppointment() {
		int customerId = 400;
		Customer customer = new Customer();
		customer.setId(customerId);
		customerRepository.save(customer);

		Appointment appointment = new Appointment();
		int appointmentId = 401;
		appointment.setId(appointmentId);
		appointment.setCustomer(customer);
		appointmentRepository.save(appointment);

		appointment = null;
		appointment = appointmentRepository.findByCustomer(customer).get(0);

		assertNotNull(appointment);
		assertEquals(appointmentId, appointment.getId());
	}

}
