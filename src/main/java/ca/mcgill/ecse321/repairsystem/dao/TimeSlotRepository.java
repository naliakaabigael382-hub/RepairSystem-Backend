package ca.mcgill.ecse321.repairsystem.dao;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "timeSlot_data" , path ="timeSlot_data")

public interface TimeSlotRepository extends CrudRepository<TimeSlot, String>{

	TimeSlot findById(int id);
	List<TimeSlot> findByStartTime(LocalDateTime time);
	List<TimeSlot> findByEndTime(LocalDateTime time);
	List<TimeSlot> findAll();
}