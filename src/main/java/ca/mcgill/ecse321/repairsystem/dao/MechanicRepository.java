package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.*;
import java.util.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "mechanic_data" , path ="mechanic_data")
public interface MechanicRepository extends CrudRepository<Mechanic, String>{

	Mechanic findById(int Id);
	List<Mechanic> findByName(String name);
	Mechanic findByPhone(long aPhone);
	Mechanic findByEmail(String email);
	List<Mechanic> findAll();
	

}