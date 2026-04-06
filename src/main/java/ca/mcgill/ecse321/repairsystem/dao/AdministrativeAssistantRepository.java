package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.repairsystem.model.AdministrativeAssistant;
import java.util.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "administrativeAssistant_data", path = "administrativeAssistant_data")
public interface AdministrativeAssistantRepository extends CrudRepository<AdministrativeAssistant, String>{
	
	AdministrativeAssistant findById(int id);
	List<AdministrativeAssistant> findByName(String name);
	AdministrativeAssistant findByPhone(long phone);
	AdministrativeAssistant findByEmail(String email);
	List<AdministrativeAssistant> findAll();

}