package ca.mcgill.ecse321.repairsystem.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "service_data" , path ="service_data")
public interface ServiceRepository extends CrudRepository<Service, String>{
	
	Service findByServiceType(ServiceType type);
	List<Service> findAll();
}
