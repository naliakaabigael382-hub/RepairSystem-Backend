package ca.mcgill.ecse321.repairsystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.repairsystem.RepairSystemApplication;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

@ExtendWith(SpringExtension.class)

@SpringBootTest(classes = RepairSystemApplication.class)
public class TestServicePersistence {

	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private MechanicRepository mechanicRepository;
	@AfterEach
	public void clearDatabase() {
		serviceRepository.deleteAll();
		mechanicRepository.deleteAll();
	}



	@Test
	public void testPersistAndLoadService() {
		Mechanic mechanic = new Mechanic();
		int Id = 700;
		mechanic.setId(Id);
		mechanicRepository.save(mechanic);

		Service service = new Service();
		int price = 701;
		service.setPrice(price);
		ServiceType serviceType = ServiceType.OilChange;
		service.setServiceType(serviceType);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		mechanics.add(mechanic);
		service.setMechanics(mechanics);
		serviceRepository.save(service);

		service = null;
		service = serviceRepository.findByServiceType(ServiceType.OilChange);
		assertNotNull(service);
		assertEquals(price, service.getPrice());
	}


}
