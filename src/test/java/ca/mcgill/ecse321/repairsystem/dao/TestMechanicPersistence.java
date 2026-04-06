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
public class TestMechanicPersistence {
	@Autowired
	private MechanicRepository mechanicRepository;

	@AfterEach
	public void clearDatabase() {

		mechanicRepository.deleteAll();

	}


	@Test
	public void testPersistAndLoadMechanic() {
		Mechanic mechanic = new Mechanic();
		int Id = 300;
		mechanic.setId(Id);
		mechanicRepository.save(mechanic);

		mechanic = null;
		mechanic = mechanicRepository.findById(Id);

		assertNotNull(mechanic);
		assertEquals(Id, mechanic.getId());
	}

}
