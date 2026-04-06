package ca.mcgill.ecse321.repairsystem.dto;

import java.util.*;
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;

public class CarDto {
	private int carId;
	private CarType carType;
	private CustomerDto customer;
	private boolean winterTires;
	private int numOfKilometers;
	private List<AppointmentDto> appointments;

	public CarDto(int id, CarType type, boolean winterTires, int numOfKm, List<AppointmentDto> appointments, CustomerDto customer) {
		carId = id;
		this.carType = type;
		this.winterTires = winterTires;
		numOfKilometers = numOfKm;
		this.appointments = appointments;
		this.customer = customer;
	}

	public CarDto(int id) {
		this.carId = id;
	}

	public CarDto() {

	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer)
	{
		this.customer = customer;
	}

	public int getId() {
		return carId;
	}

	public CarType getCarType(){
		return carType;
	}

	public void setCarType(CarType c){
		this.carType = c;
	}

	public boolean getWinterTires(){
		return winterTires;
	}


	public int getNumOfKilometers(){
	    return numOfKilometers;
	}

	public List<AppointmentDto> getAppointments() {
	    return appointments;
	}

	public void setAppointments(List<AppointmentDto> appointments)
	{
		this.appointments = appointments;
	}
}
