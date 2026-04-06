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
public class TestAdministrativeAssistantPersistence {

	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;


	@AfterEach
	public void clearDatabase() {

		administrativeAssistantRepository.deleteAll();

	}

	@Test
	public void testPersistAndLoadAdministrativeAssistant() {
		AdministrativeAssistant assistant = new AdministrativeAssistant();
		int Id = 100;
		assistant.setId(Id);
		administrativeAssistantRepository.save(assistant);

		assistant = null;
		assistant = administrativeAssistantRepository.findById(Id);

		assertNotNull(assistant);
		assertEquals(Id, assistant.getId());

	}


}
