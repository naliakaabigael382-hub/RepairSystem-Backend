package ca.mcgill.ecse321.repairsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse321.repairsystem.dao.*;

@Service
public class MechanicService {

	@Autowired
	private MechanicRepository mechanicRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private TimeSlotRepository timeslotRepository;
	@Autowired
	private TimeSlotService timeslotService;
	////////////////////SERVICE MECHANIC METHODS //////////////////// 

	/**
	 * Creates a mechanic and saves new mechanic object in the database
	 * @param aName
	 * @param aPassword
	 * @param aPhone
	 * @param aEmail
	 * @param allCapabilities
	 * @return
	 */
	@Transactional
	public Mechanic createMechanic(String aName, String aPassword, long aPhone, String aEmail, List<ca.mcgill.ecse321.repairsystem.model.Service> allCapabilities) {

		if(aName == null || aName.trim().length() == 0)
		{
			throw new IllegalArgumentException("Mechanic name cannot be empty!");

		}else if (aPassword == null || aPassword.trim().length() == 0)
		{
			throw new IllegalArgumentException("Mechanic password cannot be empty!");
		}else if (aEmail == null || aEmail.trim().length() == 0)
		{
			throw new IllegalArgumentException("Mechanic email cannot be empty!");
		}
		int id = aEmail.hashCode();
		Mechanic mechanic = new Mechanic(aName, id, aPassword, aPhone, aEmail, allCapabilities);
		mechanicRepository.save(mechanic);
		return mechanic;
	}

	/**
	 * Getter method to obtain mechanic by id
	 * @param id
	 * @return associated mechanic
	 */
	@Transactional 
	public Mechanic getMechanicById(int id) {
		Mechanic mechanic = mechanicRepository.findById(id);
		return mechanic;
	}
	
	

	/**
	 * Getter method to obtain all mechanics by searching by specific name
	 * @param name
	 * @return the list of mechanics
	 */
	@Transactional 
	public List<Mechanic> getMechanicsByName(String name) {
		List<Mechanic> mechanics = toList(mechanicRepository.findByName(name));
		return mechanics;
	}

	
	/**
	 * Getter method to obtain a mechanic object by searching by a specific phone number
	 * @param aPhone
	 * @return
	 */
	@Transactional 
	public Mechanic getMechanicByNumber(long aPhone) {
		Mechanic mechanic = mechanicRepository.findByPhone(aPhone);
		return mechanic;
	}

	/**
	 * Getter method to obtain a mechanic by searching by a specific email address
	 * @param email
	 * @return mechanic object associated to the email
	 */
	@Transactional 
	public Mechanic getMechanicByEmail(String email) {
		Mechanic mechanic = mechanicRepository.findByEmail(email);
		return mechanic;
	}
	
	@Transactional
	public Mechanic addTimeSlots(String oldEmail, String[] timeslotsStart, String[] timeslotsEnd) {
		Mechanic mechanic = mechanicRepository.findByEmail(oldEmail);
		List<TimeSlot> slots = mechanic.getTimeSlots();
		mechanic.setTimeSlots(new ArrayList<TimeSlot>());
		mechanicRepository.save(mechanic);
		for(TimeSlot slot: slots) {
			slot.removeMechanic(mechanic);
			if(slot.getAppointments().size() == 0 && slot.getMechanics().size() == 0) {
				timeslotService.deleteTimeSlot(slot.getId());
			} else {
				timeslotRepository.save(slot);
			}
		}
		String startTime = "";
		String endTime = "";
		List<TimeSlot> timeslotList = new ArrayList<TimeSlot>();
		int day = 5;
		for(int i = 0; i < timeslotsStart.length-1; i++) {
			startTime = "2021-04-0".concat(day + "-" + timeslotsStart[i]);
			endTime = "2021-04-0".concat(day + "-" + timeslotsEnd[i]);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
			LocalDateTime start = LocalDateTime.parse(startTime, formatter);
			LocalDateTime end = LocalDateTime.parse(endTime, formatter);
			int id = start.hashCode() * end.hashCode();
			TimeSlot t = timeslotRepository.findById(id);
			if(t == null) {
				t = timeslotService.createTimeSlot(start, end);
			}
			t.addMechanic(mechanic);
			timeslotRepository.save(t);
			timeslotList.add(t);
			day++;
		}
		startTime = "2021-".concat("04-10-" + timeslotsStart[timeslotsStart.length-1]);
		endTime = "2021-".concat("04-10-" + timeslotsEnd[timeslotsEnd.length-1]);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime start = LocalDateTime.parse(startTime, formatter);
		LocalDateTime end = LocalDateTime.parse(endTime, formatter);
		int id = start.hashCode() * end.hashCode();
		TimeSlot t = timeslotRepository.findById(id);
		if(t == null) {
			t = timeslotService.createTimeSlot(start, end);
		}
		t.addMechanic(mechanic);
		timeslotRepository.save(t);
		timeslotList.add(t);
		mechanic.setTimeSlots(timeslotList);
		mechanicRepository.save(mechanic);
		return mechanic;
	}

	/**
	 * Add a service to a mechanic and updating the mechanic and service tables in database
	 * @param service
	 * @param mechanic
	 * @return mechanic object
	 */
	@Transactional 
	public Mechanic addService(ca.mcgill.ecse321.repairsystem.model.Service service, Mechanic mechanic) {
		if(!mechanic.getServices().contains(service)) {
			mechanic.addService(service);
			service.addMechanic(mechanic);
			mechanicRepository.save(mechanic);
			serviceRepository.save(service);
		}
		return mechanic;
	}

	/**
	 * Removing a service from a mechanic and updating the mechanic and service table in database
	 * @param service
	 * @param mechanic
	 * @return mechanic
	 */
	@Transactional 
	public Mechanic removeService(ca.mcgill.ecse321.repairsystem.model.Service service, Mechanic mechanic) {
		if(mechanic.getServices().contains(service)) {
			mechanic.removeService(service);
			service.removeMechanic(mechanic);
			mechanicRepository.save(mechanic);
			serviceRepository.save(service);
		}
		return mechanic;
	}

	/**
	 * Obtain all the mechanics of the database
	 * @return mechanic
	 */
	@Transactional
	public List<Mechanic> getAllMechanics() {
		return toList(mechanicRepository.findAll());
	}


	@Transactional
	public Mechanic editMechanic(String email, String name, String password, String phone) { 
		Mechanic mechanic = mechanicRepository.findByEmail(email);
		mechanic.setName(name);
		mechanic.setPassword(password);
		mechanic.setPhone(Long.parseLong(phone));
		mechanicRepository.save(mechanic);
		return mechanic;
	}
	
	@Transactional
	public void deleteMechanic(int id)
	{
		if(mechanicRepository.findById(id) == null)
		{
			throw new IllegalArgumentException("No mechanic with id: " + id + "exists");
		}
		Mechanic m = mechanicRepository.findById(id);
		for(ca.mcgill.ecse321.repairsystem.model.Service service: m.getServices()) {
			service.removeMechanic(m);
			serviceRepository.save(service);
		}
		List<TimeSlot> slotList = m.getTimeSlots();
		m.setTimeSlots(null);
		mechanicRepository.save(m);
		for(TimeSlot t: slotList) {
			t.removeMechanic(m);
			timeslotRepository.save(t);
			if(t.getAppointments().size() == 0 && t.getMechanics().size() == 0) {
				timeslotService.deleteTimeSlot(t.getId());
			}
		}
		mechanicRepository.delete(m);
	}
	
	
	/* 
	 * helper method
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}