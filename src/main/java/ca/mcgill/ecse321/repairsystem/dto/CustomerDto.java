package ca.mcgill.ecse321.repairsystem.dto;

import java.util.*;

public class CustomerDto extends PersonDto{
	private String creditHash;
	private String debitHash;
	private String address;
	private List<AppointmentDto> appointments;
	private List<CarDto> cars;
	private Calendar lastActive;

	public CustomerDto(String aName,  int id, String aPassword, long aPhone, String aEmail, Calendar lastDate, String credit, String debit, String add) {
		super(aName, id, aPassword, aPhone, aEmail);
		creditHash = credit;
		debitHash = debit;
		address = add;
		lastActive = lastDate;
	}
	
	public CustomerDto(int id){
		super(id);
	}
	
	public CustomerDto() {
	}
	
	public String getCreditHash() {
		return creditHash;
	}
	
	public String getDebitHash() {
		return debitHash;
	}
	public String getAddress() {
		return address;
	}
	  
	public List<AppointmentDto> getAppointments() {
	    return appointments;
	}
	
	public void setAppointments(List<AppointmentDto> appointmentsDto) {
	    appointments = appointmentsDto;
	}
	
	public List<CarDto> getCars() {
		return cars;
	}
	
	public void setCars(List<CarDto> cars) {
		this.cars = cars;
	}
	
	public Calendar getLastActive() {
		return lastActive;
	}

}
