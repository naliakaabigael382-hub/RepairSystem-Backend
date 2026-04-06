package ca.mcgill.ecse321.repairsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.repairsystem.dto.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class ServiceRestController {

	@Autowired
	private ServiceService serviceService;
	@Autowired
	private MechanicService mechanicService;
	@Autowired
	private AppointmentService appointmentService;
	/**
	 *restful controller for all services
	 * */
	@GetMapping(value = { "/services", "/services/"})
	public List<ServiceDto> getAllServices() {
		List<Service> services = serviceService.getAllServices();
		List<ServiceDto> servicesDto = new ArrayList<ServiceDto>();
		for(Service service: services) {
			servicesDto.add(Converter.convertToDto(service));
		}
		return servicesDto;
	}
	/**
	 *restful controller for getting service by its type
	 * */	
	@GetMapping(value = { "/services/{serviceType}", "/services/{serviceType}/"})
	public ServiceDto getServiceByServiceType(@PathVariable("serviceType") String serviceType) {
		return Converter.convertToDto(serviceService.getServiceByServiceType(ServiceType.valueOf(serviceType)));
	}
	/**
	 *restful controller for creating service
	 * */	
	@PostMapping(value = { "/services/{serviceType}", "/services/{serviceType}/" })
	public ServiceDto createService(@PathVariable("serviceType") String serviceType, @RequestParam String price) throws IllegalArgumentException {
		Service service = serviceService.createService(ServiceType.valueOf(serviceType), Integer.parseInt(price));
		return Converter.convertToDto(service);
	}
	/**
	 *restful controller for editing mechanic
	 * */	
	@PutMapping(value = { "/services/{mechanicId}", "/services/{mechanicId}/" })
	public ServiceDto editMechanic(@PathVariable("mechanicId") String mechanicId, @RequestParam String serviceType, @RequestParam String addRemove) throws IllegalArgumentException {
		Service service = serviceService.getServiceByServiceType(ServiceType.valueOf(serviceType));
		Mechanic mechanic = mechanicService.getMechanicById(Integer.parseInt(mechanicId));
		if(addRemove.equals("add")) {
			serviceService.addMechanic(mechanic, service);
		} else if(addRemove.equals("remove")){
			serviceService.removeMechanic(mechanic, service);
		}
		return Converter.convertToDto(service);
	}
	/**
	 *restful controller for editing appointment
	 * */	
	@PutMapping(value = { "/services/{appointmentId}", "/services/{appointmentId}/" })
	public ServiceDto editAppointment(@PathVariable("appointmentId") String appointmentId, @RequestParam String serviceType, @RequestParam String addRemove) throws IllegalArgumentException {
		Service service = serviceService.getServiceByServiceType(ServiceType.valueOf(serviceType));
		Appointment appointment = appointmentService.getAppointmentById(Integer.parseInt(appointmentId));
		if(addRemove.equals("add")) {
			serviceService.addAppointment(appointment, service);
		} else if(addRemove.equals("remove")){
			serviceService.removeAppointment(appointment, service);
		}
		return Converter.convertToDto(service);
	}
	
	/*
	@DeleteMapping(value = { "/services/{serviceType}", "/services/{serviceType}/"})
	public void deleteService(ServiceType serviceType) {
		Service service = serviceService.getServiceByServiceType(serviceType);
		service.delete();
	}
	*/
	
}




