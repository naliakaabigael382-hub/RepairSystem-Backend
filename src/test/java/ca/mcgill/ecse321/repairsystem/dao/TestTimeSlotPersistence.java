package ca.mcgill.ecse321.repairsystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime;
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
public class TestTimeSlotPersistence {
	
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@AfterEach
	public void clearDatabase() {
		timeSlotRepository.deleteAll();
		
	}
	
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		TimeSlot timeSlot = new TimeSlot();
		int timeSlotId = 800;
		timeSlot.setId(timeSlotId);
		LocalDateTime start = LocalDateTime.now();
		timeSlot.setStartTime(start);
		timeSlotRepository.save(timeSlot);
		
		timeSlot = null;
		timeSlot = timeSlotRepository.findByStartTime(start).get(0);
		
		assertNotNull(timeSlot);
		assertEquals(timeSlotId, timeSlot.getId());
	}

}
