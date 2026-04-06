package ca.mcgill.ecse321.repairsystem.controller;

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
public class AppointmentRestController {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private TimeSlotService timeSlotService;
	@Autowired
	private CarService carService;
	@Autowired
	private MechanicService mechanicService;
	@Autowired
	private ServiceService serviceService;
	/**
	 *Rest controller for getting appointment by id
	 * */
	@GetMapping(value = { "/appointment/{id}", "/appointment/{id}/"})
	public AppointmentDto getAppointmentById(@PathVariable("id") String id) {
		return Converter.convertToDto(appointmentService.getAppointmentById(Integer.parseInt(id)));
	}
	
	/**
	 *Rest controller for getting appointment by customer
	 * */
	@GetMapping(value = { "/appointments/{customerId}", "/appointment/{customerId}/"})
	public List<AppointmentDto> getAppointmentByCustomer(@PathVariable("customerId") String customerId) {
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));

		List<Appointment> appointments = appointmentService.getAppointmentsByCustomer(customer);
		List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();
		for(Appointment appointment: appointments) {
			appointmentsDto.add(Converter.convertToDto(appointment));		}
		return appointmentsDto;
	}


	/*
	/**
	 *Rest controller for getting all appointments
	 * */
	@GetMapping(value = { "/appointment", "/appointment/"})
	public List<AppointmentDto> getAllAppointments() {
		List<Appointment> appointments = appointmentService.getAllAppointments();
		List<AppointmentDto> appointmentsDto = new ArrayList<AppointmentDto>();
		for(Appointment appointment: appointments) {
			appointmentsDto.add(Converter.convertToDto(appointment));		}
		return appointmentsDto;
	}
	/**
	 *Rest controller for creating appointment
	 * */
	@PostMapping(value = { "/appointment/{customerId}", "/appointment/{customerId}/"})
	public AppointmentDto createAppointment(@PathVariable("customerId") String customerId, @RequestParam String timeSlotId,  @RequestParam String carId, @RequestParam String[] services,  @RequestParam(defaultValue = "") String note) throws IllegalArgumentException {
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		TimeSlot timeslot = timeSlotService.getTimeSlotById(Integer.parseInt(timeSlotId));
		Car car = carService.getCarById(Integer.parseInt(carId));
		List<Service> serviceList = new ArrayList<Service>();
		for(String service: services) {
			serviceList.add(serviceService.getServiceByServiceType(ServiceType.valueOf(service)));
		}
		Appointment appointment = appointmentService.createApp(customer, timeslot, car, serviceList, note);
		return Converter.convertToDto(appointment);
	}
	/**
	 *Rest controller for editing appointment
	 * */
	@PutMapping(value = { "/appointment/editAppointment/{appointmentId}", "/appointment/editAppointment/{appointmentId}/"})
	public AppointmentDto editAppointment(@PathVariable("appointmentId") String appointmentId, @RequestParam String status) throws IllegalArgumentException {
		Appointment appointment = appointmentService.getAppointmentById(Integer.parseInt(appointmentId));
		appointment = appointmentService.editApp(appointment, status);
		return Converter.convertToDto(appointment);
	}
	/**
	 *Rest controller for adding mechanic
	 * */
	@PutMapping(value = { "/appointment/addMechanic/{mechanicId}", "/appointment/addMechanic/{mechanicId}/"})
	public AppointmentDto addMechanic(@PathVariable("mechanicId") String mechanicId, @RequestParam String appointmentId) throws IllegalArgumentException {
		Appointment appointment = appointmentService.getAppointmentById(Integer.parseInt(appointmentId));	
		Mechanic mechanic = mechanicService.getMechanicById(Integer.parseInt(mechanicId));
		appointmentService.addMechanic(appointment, mechanic);
		return Converter.convertToDto(appointment);
	}

	/**
	 *Rest controller for adding service
	 * */
	@PutMapping(value = { "/appointment/addService/{serviceType}", "/appointment/addService/{serviceType}/"})
	public AppointmentDto addService(@PathVariable("serviceType") String serviceType, @RequestParam String timeSlotId, @RequestParam String customerId) throws IllegalArgumentException {
		TimeSlot timeSlot = timeSlotService.getTimeSlotById(Integer.parseInt(timeSlotId));
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));		
		Appointment appointment = appointmentService.getAppointmentById(customer.hashCode()*timeSlot.hashCode());	
		Service service = serviceService.getServiceByServiceType(ServiceType.valueOf(serviceType));
		appointmentService.addService(appointment, service);
		return Converter.convertToDto(appointment);
	}
	
	/**
	 *Rest controller for deleting appointment
	 * */
	@DeleteMapping(value = { "/appointment/{appointmentId}", "/appointment/{customerId}/"})
	public AppointmentDto deleteAppointment(@PathVariable("appointmentId") String appointmentId) throws IllegalArgumentException {
		Appointment appointment = appointmentService.getAppointmentById(Integer.parseInt(appointmentId));
		appointment = appointmentService.deleteApp(appointment);
		return Converter.convertToDto(appointment);
	}

}




