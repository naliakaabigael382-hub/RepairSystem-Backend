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
public class TestImagePersistence {


	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private ImageRepository imageRepository;


	@AfterEach
	public void clearDatabase() {
		imageRepository.deleteAll();
		appointmentRepository.deleteAll();
	}


	@Test
	public void testPersistAndLoadImage() {
		Appointment appointment = new Appointment();
		int appointmentId = 601;
		appointment.setId(appointmentId);
		appointmentRepository.save(appointment);

		Image image = new Image();
		int  imageId = 601;
		image.setId(imageId);
		image.setAppointment(appointment);
		imageRepository.save(image);

		image = null;
		image = imageRepository.findByAppointment(appointment).get(0);

		assertNotNull(image);
		assertEquals(imageId, image.getId());

	}
}
