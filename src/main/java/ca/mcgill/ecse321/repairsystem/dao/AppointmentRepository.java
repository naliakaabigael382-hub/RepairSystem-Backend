package ca.mcgill.ecse321.repairsystem.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="appointment_data" , path = "appointment_data")
public interface AppointmentRepository extends CrudRepository<Appointment, String>{
	
	List<Appointment> findByCustomer(Customer customer);
	List<Appointment> findByTimeSlot(TimeSlot time);
	List<Appointment> findByCar(Car car);
	List<Appointment> findByStatus(AppointmentStatus status);
	Appointment findById(int id);
	List<Appointment> findAll();
	
}