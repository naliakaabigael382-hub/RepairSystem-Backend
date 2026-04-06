package ca.mcgill.ecse321.repairsystem.dto;

import java.util.*;

public class MechanicDto extends PersonDto{

	private List<TimeSlotDto> timeSlots;
	private List<ServiceDto> services;
	private List<AppointmentDto> appointments;

	public MechanicDto(String aName, int id, String aPassword, long aPhone, String aEmail, List<ServiceDto> allCapabilities) {
		super(aName, id, aPassword, aPhone, aEmail);    
	}
	
	public MechanicDto(int Id) {
		super(Id);
	}
	
	public MechanicDto() {
		
	}
	
	public List<TimeSlotDto> getTimeSlots() {
		return timeSlots;
	}
	
	public void setTimeSlots(List<TimeSlotDto> timeslots) {
		this.timeSlots = timeslots;
	}
	
	public List<ServiceDto> getServices() {
		return services;
	}
	
	public void setServices(List<ServiceDto> services) {
		this.services = services;
	}
	
	public List<AppointmentDto> getAppointments() {
	    return appointments;
	}
	
	public void setAppointments(List<AppointmentDto> appointments) {
	    this.appointments = appointments;
	}
	
}
