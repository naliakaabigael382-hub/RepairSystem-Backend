package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

@Service
public class ServiceService {
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private MechanicRepository mechanicRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	/**
	 * Creates a service and saves new service object in the database
	 * @param aType
	 * @param price
	 * @return
	 */
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service createService(ServiceType aType, int price) {
		
		if(aType == null)
		{
			throw new IllegalArgumentException("Service type cannot be null");
		} 
		//if price is 0 or less than 0
		
		ca.mcgill.ecse321.repairsystem.model.Service service = new ca.mcgill.ecse321.repairsystem.model.Service(aType, price);
		serviceRepository.save(service);
		return service;
	}
	

	/**
	 * Get a service by a type
	 * @param type
	 * @return service
	 */
	@Transactional
	public ca.mcgill.ecse321.repairsystem.model.Service getServiceByServiceType(ServiceType type) {
		return serviceRepository.findByServiceType(type);
	}
	
	/**
	 * Getter method to obtain all services in the database
	 * @return list of service
	 */
	@Transactional
	public List<ca.mcgill.ecse321.repairsystem.model.Service> getAllServices() {
		return serviceRepository.findAll();
	}
	
	/**
	 * Add mechanics to a service and update/save the service and mechanics tables in database
	 * @param mechanic
	 * @param service
	 */
	@Transactional
	public void addMechanic(Mechanic mechanic, ca.mcgill.ecse321.repairsystem.model.Service service) {
		service.addMechanic(mechanic);
		mechanic.addService(service);
		serviceRepository.save(service);
		mechanicRepository.save(mechanic);
	}
	
	/**
	 * Add an appointment given a service  and update/save the service and appointment tables in database
	 * @param appointment
	 * @param service
	 */
	@Transactional
	public void addAppointment(Appointment appointment, ca.mcgill.ecse321.repairsystem.model.Service service) {
		service.addAppointment(appointment);
		appointment.addService(service);
		serviceRepository.save(service);
		appointmentRepository.save(appointment);
	}
	
	/**
	 * Remove mechanic from a service and vice versa. Update/Save the service and mechanic
	 * @param mechanic
	 * @param service
	 */
	@Transactional
	public void removeMechanic(Mechanic mechanic, ca.mcgill.ecse321.repairsystem.model.Service service) {
		service.removeMechanic(mechanic);
		mechanic.removeService(service);
		serviceRepository.save(service);
		mechanicRepository.save(mechanic);
	}
	
	/**
	 * Remove mechanic from a service and vice versa. Update/Save the service and appointment
	 * @param mechanic
	 * @param service
	 */
	@Transactional
	public void removeAppointment(Appointment appointment, ca.mcgill.ecse321.repairsystem.model.Service service) {
		service.removeAppointment(appointment);
		appointment.removeService(service);
		serviceRepository.save(service);
		appointmentRepository.save(appointment);
	}
	
}
