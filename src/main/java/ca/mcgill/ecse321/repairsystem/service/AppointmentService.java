package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;

@Service
public class AppointmentService {
	//////////////////// SERVICE APPOINTMENT METHODS ////////////////////
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TimeSlotRepository timeslotRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private MechanicRepository mechanicRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private ImageRepository imageRepository;

	/**
	 * Creating an appointment given a customer, time, car, note
	 * @param customer
	 * @param time
	 * @param car
	 * @param note
	 * @return
	 */
	@Transactional
	public Appointment createApp(Customer customer, TimeSlot time, Car car, List<ca.mcgill.ecse321.repairsystem.model.Service> services, String note) {
		// input validation
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null");
		} else if (time == null) {
			throw new IllegalArgumentException("TimeSlot cannot be null");
		} else if (car == null) {
			throw new IllegalArgumentException("Car cannot be null");
		}

		int id = customer.hashCode() * time.hashCode();
		Appointment app = new Appointment(customer, id, time, car, note);
		app.setServices(services);
		customer.addAppointment(app);
		time.addAppointment(app);
		car.addAppointment(app);
		appointmentRepository.save(app);
		for (ca.mcgill.ecse321.repairsystem.model.Service service : services) {
			service.addAppointment(app);
			serviceRepository.save(service);
		}
		customerRepository.save(customer);
		timeslotRepository.save(time);
		carRepository.save(car);
		return app;
	}

	@Transactional
	public Appointment editApp(Appointment appointment, String status) {
		appointment.setStatus(AppointmentStatus.valueOf(status));
		appointmentRepository.save(appointment);
		return appointment;
	}

	@Transactional
	public Appointment deleteApp(Appointment appointment) {
		for (Mechanic m : appointment.getMechanics()) {
			m.removeAppointment(appointment);
			mechanicRepository.save(m);
		}
		for (ca.mcgill.ecse321.repairsystem.model.Service s : appointment.getServices()) {
			s.removeAppointment(appointment);
			serviceRepository.save(s);
		}
		TimeSlot t = appointment.getTimeSlot();
		t.removeAppointment(appointment);
		timeslotRepository.save(t);
		Car car = appointment.getCar();
		Customer c = appointment.getCustomer();
		car.removeAppointment(appointment);
		c.removeAppointment(appointment);
		customerRepository.save(c);
		carRepository.save(car);
		appointmentRepository.delete(appointment);
		return appointment;
	}

	/**
	 * Getter methods to obtain a customer's list of appointments
	 * @param customer
	 * @return
	 */
	@Transactional
	public List<Appointment> getAppointmentsByCustomer(Customer customer) {
		List<Appointment> appointments = toList(appointmentRepository.findByCustomer(customer));
		return appointments;
	}

	/**
	 * Getter methods to obtain a customer's list of car
	 * @param car
	 * @return the list of appointments
	 */
	@Transactional
	public List<Appointment> getAppointmentsByCar(Car car) {
		List<Appointment> appointments = toList(appointmentRepository.findByCar(car));
		return appointments;
	}

	/**
	 * Getter methods to obtain a customer's list of appointments by searching by appointment status
	 * @param status
	 * @return the list of appointments
	 */
	@Transactional
	public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
		List<Appointment> appointments = toList(appointmentRepository.findByStatus(status));
		return appointments;
	}

	/**
	 * Getter methods to obtain a customer's list of appointments by searching by timeslot
	 * @param time
	 * @return the list of appointments
	 */
	@Transactional
	public List<Appointment> getAppointmentsByTimeSlot(TimeSlot time) {
		List<Appointment> appointments = toList(appointmentRepository.findByTimeSlot(time));
		return appointments;
	}

	/**
	 * Getter methods to obtain a list of a customer's appointment by searching by id
	 * @param id
	 * @return appointment
	 */
	@Transactional
	public Optional<Appointment> findById(int id) {
		return Optional.ofNullable(appointmentRepository.findById(id));
	}

	@Transactional
	public Appointment getAppointmentById(int id) {
		Optional<Appointment> optionalAppointment = findById(id);
		return optionalAppointment.orElse(null); // Return `null` if appointment is not found
	}



	/**
	 * Getter method to obtain all the appointments in the database
	 * @return list of appointments
	 */
	@Transactional
	public List<Appointment> getAllAppointments() {
		return toList(appointmentRepository.findAll());
	}

	/**
	 * Adding a mechanic to an appointment
	 * @param appointment
	 * @param mechanic
	 */
	@Transactional
	public void addMechanic(Appointment appointment, Mechanic mechanic) {
		appointment.addMechanic(mechanic);
		mechanic.addAppointment(appointment);
		appointmentRepository.save(appointment);
		mechanicRepository.save(mechanic);
	}

	/**
	 * Adding a service to an appointment
	 * @param appointment
	 * @param service
	 */
	@Transactional
	public void addService(Appointment appointment, ca.mcgill.ecse321.repairsystem.model.Service service) {
		appointment.addService(service);
		service.addAppointment(appointment);
		appointmentRepository.save(appointment);
		serviceRepository.save(service);
	}

	/**
	 * Adding an image to an appointment
	 * @param appointment
	 * @param image
	 */
	@Transactional
	public void addImage(Appointment appointment, Image image) {
		appointment.addImage(image);
		image.setAppointment(appointment);
		appointmentRepository.save(appointment);
		imageRepository.save(image);
	}

	/*
	 * Helper method to convert iterable to list
	 */
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
