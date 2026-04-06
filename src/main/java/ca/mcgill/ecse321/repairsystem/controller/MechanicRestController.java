package ca.mcgill.ecse321.repairsystem.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class MechanicRestController {

	@Autowired
	private ServiceService serviceService;
	@Autowired
	private MechanicService mechanicService;
	/**
	 *restful controller for getting mechanic
	 * */
	@GetMapping(value = { "/mechanics", "/mechanics/"})
	public List<MechanicDto> getAllMechanics() {
		List<Mechanic> mechanics = mechanicService.getAllMechanics();
		List<MechanicDto> mechanicsDto = new ArrayList<MechanicDto>();
		for(Mechanic mechanic: mechanics) {
			mechanicsDto.add(Converter.convertToDto(mechanic));
		}
		return mechanicsDto;
	}
	
	/**
	 *restful controller for getting id
	 * */
	@GetMapping(value = { "/mechanic/{id}", "/mechanic/{id}/"})
	public MechanicDto getMechanicById(@PathVariable("id") String id) {
		return Converter.convertToDto(mechanicService.getMechanicById(Integer.parseInt(id)));
	}
	
	

	
	/**
	 *restful controller for getting customer by id
	 * */
	@GetMapping(value = { "/mechanic/login/{email}", "/mechanic/login/{email}/"})
	public MechanicDto getMechanicFromLogin(@PathVariable("email") String email, @RequestParam String password) {
		Mechanic m = mechanicService.getMechanicByEmail(email);
		if(m != null && password.equals(m.getPassword())) {
			return Converter.convertToDto(m);
		}
		return null;
	}
	
	/**
	 *restful controller for creating mechanic
	 * */
	@PostMapping(value = { "/mechanic/{name}", "/mechanic/{name}/" })
	public MechanicDto createMechanic(@PathVariable("name") String name, @RequestParam String password, @RequestParam String phone, @RequestParam String email) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.createMechanic(name, password, Long.parseLong(phone), email, new ArrayList<Service>());
		return Converter.convertToDto(mechanic);
	}
	
	/**
	 *restful controller for editing mechanic
	 * */
	@PutMapping(value = { "/mechanic/{oldEmail}", "/mechanic/{oldEmail}/" })
	public MechanicDto editMechanic(@PathVariable("oldEmail") String oldEmail, @RequestParam String name, @RequestParam String password, @RequestParam String phone) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.editMechanic(oldEmail, name, password, phone);
		return Converter.convertToDto(mechanic);
	}
	
	/**
	 *restful controller for editing mechanic
	 * */
	@PutMapping(value = { "/mechanic/addTimeSlots/{oldEmail}", "/mechanic/addTimeSlots/{oldEmail}/" })
	public MechanicDto addMechanicTimeSlots(@PathVariable("oldEmail") String oldEmail, @RequestParam String[] timeslotsStart, @RequestParam String[] timeslotsEnd) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.addTimeSlots(oldEmail, timeslotsStart, timeslotsEnd);
		return Converter.convertToDto(mechanic);
	}
	
	/**
	 *restful controller for editing service
	 * */
	@PutMapping(value = { "/mechanic/editService/{serviceType}", "/mechanic/editService/{serviceType}/" })
	public MechanicDto editService(@PathVariable("serviceType") String serviceType, @RequestParam String addRemove, @RequestParam String oldEmail) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.getMechanicByEmail(oldEmail);
		ServiceType service = ServiceType.valueOf(serviceType);
		Service s = serviceService.getServiceByServiceType(service);
		if(addRemove.equals("add")) {
			mechanicService.addService(s, mechanic);
		} else if(addRemove.equals("remove")) {
			mechanicService.removeService(s, mechanic);
		}
		return Converter.convertToDto(mechanic);
	}
	
	/**
	 *restful controller for editing service
	 * */
	@PutMapping(value = { "/mechanic/updateServices/{oldEmail}", "/mechanic/updateServices/{oldEmail}/" })
	public MechanicDto editService(@PathVariable("oldEmail") String oldEmail, @RequestParam String[] services) throws IllegalArgumentException {
		Mechanic mechanic = mechanicService.getMechanicByEmail(oldEmail);
		for(Service service: mechanic.getServices()){
			service.removeMechanic(mechanic);
		}
		mechanic.setServices(new ArrayList<Service>());
		for(String serviceType: services) {
			mechanicService.addService(serviceService.getServiceByServiceType(ServiceType.valueOf(serviceType)), mechanic);
		}
		return Converter.convertToDto(mechanic);
	}
	
	@DeleteMapping(value= {"/mechanic/{id}", "/mechanic/{id}/"})
	public boolean removeMechanic(@PathVariable("id") String id)
	{
		mechanicService.deleteMechanic(Integer.parseInt(id));
		return true;
		
	}
	
	

}




