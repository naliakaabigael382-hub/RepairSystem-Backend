package ca.mcgill.ecse321.repairsystem.dto;

import java.util.List;

public class AppointmentDto {

	// Enum for appointment status
	public enum AppointmentStatus {
		AppointmentBooked,
		CarReceived,
		InRepair,
		Completed
	}

	private CustomerDto customer;
	private Integer id; // Changed to Integer to align with nullable cases if needed
	private AppointmentStatus status;
	private String note;
	private TimeSlotDto timeslot;
	private List<MechanicDto> mechanics;
	private CarDto car;
	private List<ImageDto> images;
	private List<ServiceDto> services;

	// Default constructor
	public AppointmentDto() {
		this.status = AppointmentStatus.AppointmentBooked; // Default status
	}

	// Constructor with minimal fields
	public AppointmentDto(Integer id) {
		this.id = id;
		this.status = AppointmentStatus.AppointmentBooked;
	}

	// Constructor with all fields
	public AppointmentDto(CustomerDto customer, Integer id, TimeSlotDto timeslot, List<MechanicDto> mechanics,
						  CarDto car, List<ImageDto> images, List<ServiceDto> services, String note, String status) {
		this.customer = customer;
		this.id = id;
		this.timeslot = timeslot;
		this.mechanics = mechanics;
		this.car = car;
		this.images = images;
		this.services = services;
		this.note = note;
		this.status = AppointmentStatus.valueOf(status);
	}

	// Getters
	public CustomerDto getCustomer() {
		return customer;
	}

	public Integer getId() {
		return id;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public String getNote() {
		return note;
	}

	public TimeSlotDto getTimeSlot() {
		return timeslot;
	}

	public List<MechanicDto> getMechanics() {
		return mechanics;
	}

	public CarDto getCar() {
		return car;
	}

	public List<ImageDto> getImages() {
		return images;
	}

	public List<ServiceDto> getServices() {
		return services;
	}

	// Setters
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setTimeSlot(TimeSlotDto timeslot) {
		this.timeslot = timeslot;
	}

	public void setMechanics(List<MechanicDto> mechanics) {
		this.mechanics = mechanics;
	}

	public void setCar(CarDto car) {
		this.car = car;
	}

	public void setImages(List<ImageDto> images) {
		this.images = images;
	}

	public void setServices(List<ServiceDto> services) {
		this.services = services;
	}
}
