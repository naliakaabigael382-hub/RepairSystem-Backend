package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Appointment.AppointmentStatus;
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;


@ExtendWith(MockitoExtension.class)
public class TestAppointmentService {
	@Mock
	private AppointmentRepository appointmentDao;
	
	@Mock
	private CustomerRepository customerDao;
	
	@Mock
	private TimeSlotRepository timeSlotDao;
	
	@Mock
	private CarRepository carDao;
	
	@Mock
	private MechanicRepository mechanicDao;
	
	@Mock
	private ImageRepository imageDao;
	
	@Mock
	private ServiceRepository serviceDao;
	
	
	@InjectMocks
	private AppointmentService service;
	
	//fields for creating an appointment 
	private static  int APPOINTMENT_ID= 45;
	private static AppointmentStatus APPOINTMENT_STATUS= AppointmentStatus.InRepair;
	private static String APPOINTMENT_NOTE = "SomDescription"; 

	//fields for creating a customer 
	private static Customer CUSTOMER = new Customer("TestPerson", 2001, "123abc", 76523455,"TestPerson@gmail.com", "123456789","987654321", "123 Street Avenue");

	//fields for creating timeslot 
	private static TimeSlot TIME_SLOT = new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
	private static Car CAR = new Car(40567, CarType.SEDAN,false, 170000,  CUSTOMER);
	
	private static  int APPOINTMENT_ID2= 45;
	private static AppointmentStatus APPOINTMENT_STATUS2= AppointmentStatus.InRepair;
	private static String APPOINTMENT_NOTE2 = "SomDescription"; 

	//fields for creating a customer 
	private static Customer CUSTOMER2 = new Customer("TestPerson", 2001, "123abc", 76523455,"TestPerson@gmail.com", "123456789","987654321", "123 Street Avenue");

	//fields for creating timeslot 
	private static TimeSlot TIME_SLOT2 = new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
	private static Car CAR2 = new Car(40567, CarType.SEDAN,false, 170000,  CUSTOMER);
	@BeforeEach
	public void setMockOutput() {
		
		
		lenient().when(appointmentDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(APPOINTMENT_ID)) {
				Appointment appointment = new Appointment();
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setId(APPOINTMENT_ID);
				return appointment;
			} else {
				return null;
			}
		});
		
		lenient().when(appointmentDao.findByCustomer(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
			List<Appointment> appointments = new ArrayList<Appointment>();
			
			Customer customer = invocation.getArgument(0);
			if (customer.getId() == CUSTOMER.getId()) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				appointments.add(appointment);
			}
			if (customer.getId() == CUSTOMER2.getId()) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID2);
				appointment.setNote(APPOINTMENT_NOTE2);
				appointment.setStatus(APPOINTMENT_STATUS2);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER2);
				appointment.setCar(CAR2);
				appointment.setTimeSlot(TIME_SLOT2);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS2);
				appointments.add(appointment);
			}
			return appointments;
		});
		
		lenient().when(appointmentDao.findByTimeSlot(any(TimeSlot.class))).thenAnswer((InvocationOnMock invocation) -> {
			List<Appointment> appointments = new ArrayList<Appointment>();
			TimeSlot timeSlot = invocation.getArgument(0);
			if (timeSlot.getId() == TIME_SLOT.getId()) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				appointments.add(appointment);			
			}
			if (timeSlot.getId() == TIME_SLOT2.getId()) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID2);
				appointment.setNote(APPOINTMENT_NOTE2);
				appointment.setStatus(APPOINTMENT_STATUS2);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER2);
				appointment.setCar(CAR2);
				appointment.setTimeSlot(TIME_SLOT2);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS2);
				appointments.add(appointment);
			}
			return appointments;
		});
		
		lenient().when(appointmentDao.findByCar(any(Car.class))).thenAnswer((InvocationOnMock invocation) -> {
			
			List<Appointment> appointments = new ArrayList<Appointment>();
			Car car = invocation.getArgument(0);
			if (car.getId() == CAR.getId()) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				appointments.add(appointment);
			}
			if(car.getId() == CAR2.getId()) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID2);
				appointment.setNote(APPOINTMENT_NOTE2);
				appointment.setStatus(APPOINTMENT_STATUS2);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER2);
				appointment.setCar(CAR2);
				appointment.setTimeSlot(TIME_SLOT2);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS2);
				appointments.add(appointment);
			}
			return appointments;
		});
		
		lenient().when(appointmentDao.findByStatus(any(Appointment.AppointmentStatus.class))).thenAnswer((InvocationOnMock invocation) -> {
			
			List<Appointment> appointments = new ArrayList<Appointment>();
			AppointmentStatus status  = invocation.getArgument(0);
			if (status.equals(APPOINTMENT_STATUS)) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID);
				appointment.setNote(APPOINTMENT_NOTE);
				appointment.setStatus(APPOINTMENT_STATUS);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER);
				appointment.setCar(CAR);
				appointment.setTimeSlot(TIME_SLOT);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS);
				appointments.add(appointment);	}
			if (status.equals(APPOINTMENT_STATUS2)) {
				Appointment appointment = new Appointment();
				appointment.setId(APPOINTMENT_ID2);
				appointment.setNote(APPOINTMENT_NOTE2);
				appointment.setStatus(APPOINTMENT_STATUS2);
				appointment.setImages(new ArrayList<Image>());
				appointment.setCustomer(CUSTOMER2);
				appointment.setCar(CAR2);
				appointment.setTimeSlot(TIME_SLOT2);
				appointment.setMechanics(new ArrayList<Mechanic>());
				appointment.setStatus(APPOINTMENT_STATUS2);
				appointments.add(appointment);
			}
			return appointments;
		});
		
		
		lenient().when(appointmentDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Appointment> appointments = new ArrayList<Appointment>();
			Appointment appointment = new Appointment();
			appointment.setId(APPOINTMENT_ID);
			appointment.setNote(APPOINTMENT_NOTE);
			appointment.setStatus(APPOINTMENT_STATUS);
			appointment.setImages(new ArrayList<Image>());
			appointment.setCustomer(CUSTOMER);
			appointment.setCar(CAR);
			appointment.setTimeSlot(TIME_SLOT);
			appointment.setMechanics(new ArrayList<Mechanic>());
			appointment.setStatus(APPOINTMENT_STATUS);
			appointment.setImages(new ArrayList<Image>());
			appointments.add(appointment);
			
			Appointment appointment2 = new Appointment();
			appointment2.setId(APPOINTMENT_ID2);
			appointment2.setNote(APPOINTMENT_NOTE2);
			appointment2.setStatus(APPOINTMENT_STATUS2);
			appointment2.setImages(new ArrayList<Image>());
			appointment2.setCustomer(CUSTOMER2);
			appointment2.setCar(CAR2);
			appointment2.setTimeSlot(TIME_SLOT2);
			appointment2.setMechanics(new ArrayList<Mechanic>());
			appointment2.setStatus(APPOINTMENT_STATUS2);
			appointment2.setImages(new ArrayList<Image>());
			appointments.add(appointment2);
		
			return appointments;
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(appointmentDao.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(timeSlotDao.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(carDao.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(mechanicDao.save(any(Mechanic.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(serviceDao.save(any(Service.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(imageDao.save(any(Image.class))).thenAnswer(returnParameterAsAnswer);
		
	}
	
	

	
	@Test
	/**
	 * Test the creation of the appointment object
	 */
	public void testCreateAppointment()
	{
		//assertEquals(0, service.getAllAppointments().size());
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot dummyTime = new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car dummyCar = new Car(451, CarType.HATCHBACK, true, 50000,  customer);
		String dummyNote = "dummy Note";
		int appointmentId = customer.hashCode() * dummyTime.hashCode();
		Appointment appointment = null;
		List<Service> services = new ArrayList<Service>();
		services.add(new Service(Service.ServiceType.OilChange, 10));
		
		try {
			appointment = service.createApp(customer, dummyTime, dummyCar, services, dummyNote);
		}catch(IllegalArgumentException e)
		{
			fail();
		}
		
		assertNotNull(appointment);
		assertEquals(appointmentId, appointment.getId());
		assertEquals(dummyNote, appointment.getNote());
		assertEquals(dummyCar, appointment.getCar());
		assertEquals(dummyTime, appointment.getTimeSlot());
		
	}
	


	@Test
	/**
	 * Verify that the appointment object has a customer assigned to it
	 */
	public void testCreateAppointmentNullCustomer()
	{
		String error = null;
		Customer customer  = null;
		TimeSlot dummyTime = new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car dummyCar = new Car(451, CarType.HATCHBACK, true, 50000, customer);
		String dummyNote = "dummy Note";
		Appointment appointment = null;
		List<Service> services = new ArrayList<Service>();
		services.add(new Service(Service.ServiceType.OilChange, 10));
		
		try {
			appointment = service.createApp(customer, dummyTime, dummyCar, services, dummyNote);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("Customer cannot be null", error);	
	}
	
	@Test
	/**
	 * Verify that there is a time assigned to the appointment
	 */
	public void testCreateAppointmentNullTimeSlot()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot dummyTime = null;
		Car dummyCar = new Car(451, CarType.HATCHBACK, true, 50000, customer);
		String dummyNote = "dummy Note";
		Appointment appointment = null;
		List<Service> services = new ArrayList<Service>();
		services.add(new Service(Service.ServiceType.OilChange, 10));
		
		try {
			appointment = service.createApp(customer, dummyTime, dummyCar, services, dummyNote);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("TimeSlot cannot be null", error);	
	}


	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testCreateAppointmentNullCar()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot dummyTime =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car dummyCar = null;
		String dummyNote = "dummy Note";
		Appointment appointment = null;
		List<Service> services = new ArrayList<Service>();
		services.add(new Service(Service.ServiceType.OilChange, 10));
		
		try {
			appointment = service.createApp(customer, dummyTime, dummyCar, services, dummyNote);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("Car cannot be null", error);	
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testGetAppointmentByCustomer()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot dummyTime =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car dummyCar = null;
		String dummyNote = "dummy Note";
		Appointment appointment = null;
		List<Service> services = new ArrayList<Service>();
		services.add(new Service(Service.ServiceType.OilChange, 10));
		
		try {
			appointment = service.createApp(customer, dummyTime, dummyCar, services, dummyNote);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(appointment);
		assertEquals("Car cannot be null", error);	
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testGetAppointmentsByCar()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer, time, car, services1, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		APPOINTMENT_ID = customer.hashCode()*time.hashCode();;
		APPOINTMENT_STATUS= AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE = dummyNote;
		CUSTOMER = customer;
		TIME_SLOT = time;
		CAR = car;
		 
		Customer customer2  = new Customer("Dingo", 2222, "hello", 545, "Dingo@gmail.com","666545", "1234", "sugar");
		TimeSlot time2 =  new TimeSlot(LocalDateTime.of(2020, Month.JUNE,21,14,12,00),LocalDateTime.of(2022, Month.APRIL,21, 20, 00,00), 240);
		Car car2 = car;
		String dummyNote2 = "dummy Note2";
		List<Service> services2 = new ArrayList<Service>();
		services2.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer2, time2, car2, services2, dummyNote2);
		int id2 = customer2.hashCode()*time2.hashCode();
		
		APPOINTMENT_ID2 = id2;
		APPOINTMENT_STATUS2 = AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE2 = dummyNote2;
		CUSTOMER2 = customer2;
		TIME_SLOT2 = time2;
		CAR2 = car2;
		 
		List<Appointment> appointments = new ArrayList<Appointment>();
		
		try {
			appointments = service.getAppointmentsByCar(car);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointments.get(0));
		assertEquals(appointments.get(0).getCar(), car);
		assertEquals(appointments.get(0).getCustomer(), customer);
		assertEquals(appointments.get(0).getId(), id);
		assertEquals(appointments.get(0).getTimeSlot(), time);
		assertEquals(appointments.get(0).getNote(), dummyNote);
		
		assertNotNull(appointments.get(1));
		assertEquals(appointments.get(1).getCar(), car2);
		assertEquals(appointments.get(1).getCustomer(), customer2);
		assertEquals(appointments.get(1).getId(), id2);
		assertEquals(appointments.get(1).getTimeSlot(), time2);
		assertEquals(appointments.get(1).getNote(), dummyNote2);
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testGetAppointmentsByStatus()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer, time, car, services1, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		APPOINTMENT_ID = customer.hashCode()*time.hashCode();;
		APPOINTMENT_STATUS= AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE = dummyNote;
		CUSTOMER = customer;
		TIME_SLOT = time;
		 CAR = car;
		 
		Customer customer2  = new Customer("Dingo", 2222, "hello", 545, "Dingo@gmail.com","666545", "1234", "sugar");
		TimeSlot time2 =  new TimeSlot(LocalDateTime.of(2020, Month.JUNE,21,14,12,00),LocalDateTime.of(2022, Month.APRIL,21, 20, 00,00), 240);
		Car car2 = new Car();
		car2.setId(45);
		car2.setAppointments(new ArrayList<Appointment>());
		car2.setCustomer(customer2);
		String dummyNote2 = "dummy Note2";
		List<Service> services2 = new ArrayList<Service>();
		services2.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer2, time2, car2, services2, dummyNote2);
		int id2 = customer2.hashCode()*time2.hashCode();
		
		APPOINTMENT_ID2 = id2;
		APPOINTMENT_STATUS2 = AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE2 = dummyNote2;
		CUSTOMER2 = customer2;
		TIME_SLOT2 = time2;
		CAR2 = car2;
		 
		List<Appointment> appointments = new ArrayList<Appointment>();
		
		try {
			appointments = service.getAppointmentsByStatus(AppointmentStatus.AppointmentBooked);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointments.get(0));
		assertEquals(appointments.get(0).getCar(), car);
		assertEquals(appointments.get(0).getCustomer(), customer);
		assertEquals(appointments.get(0).getId(), id);
		assertEquals(appointments.get(0).getTimeSlot(), time);
		assertEquals(appointments.get(0).getNote(), dummyNote);
		
		assertNotNull(appointments.get(1));
		assertEquals(appointments.get(1).getCar(), car2);
		assertEquals(appointments.get(1).getCustomer(), customer2);
		assertEquals(appointments.get(1).getId(), id2);
		assertEquals(appointments.get(1).getTimeSlot(), time2);
		assertEquals(appointments.get(1).getNote(), dummyNote2);	
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testGetAppointmentsByTimeSlot()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer, time, car, services1, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		APPOINTMENT_ID = customer.hashCode()*time.hashCode();;
		APPOINTMENT_STATUS= AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE = dummyNote;
		CUSTOMER = customer;
		TIME_SLOT = time;
		 CAR = car;
		 
		Customer customer2  = new Customer("Dingo", 2222, "hello", 545, "Dingo@gmail.com","666545", "1234", "sugar");
		TimeSlot time2 =  time;
		Car car2 = new Car();
		car2.setId(45);
		car2.setAppointments(new ArrayList<Appointment>());
		car2.setCustomer(customer2);
		String dummyNote2 = "dummy Note2";
		List<Service> services2 = new ArrayList<Service>();
		services2.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer2, time2, car2, services2, dummyNote2);
		int id2 = customer2.hashCode()*time2.hashCode();
		
		APPOINTMENT_ID2 = id2;
		APPOINTMENT_STATUS2 = AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE2 = dummyNote2;
		CUSTOMER2 = customer2;
		TIME_SLOT2 = time2;
		CAR2 = car2;
		 
		List<Appointment> appointments = new ArrayList<Appointment>();
		
		try {
			appointments = service.getAppointmentsByTimeSlot(time);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointments.get(0));
		assertEquals(appointments.get(0).getCar(), car);
		assertEquals(appointments.get(0).getCustomer(), customer);
		assertEquals(appointments.get(0).getId(), id);
		assertEquals(appointments.get(0).getTimeSlot(), time);
		assertEquals(appointments.get(0).getNote(), dummyNote);
		
		assertNotNull(appointments.get(1));
		assertEquals(appointments.get(1).getCar(), car2);
		assertEquals(appointments.get(1).getCustomer(), customer2);
		assertEquals(appointments.get(1).getId(), id2);
		assertEquals(appointments.get(1).getTimeSlot(), time2);
		assertEquals(appointments.get(1).getNote(), dummyNote2);	
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testGetAppointmentsByCustomer()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer, time, car, services1, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		APPOINTMENT_ID = customer.hashCode()*time.hashCode();;
		APPOINTMENT_STATUS= AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE = dummyNote;
		CUSTOMER = customer;
		TIME_SLOT = time;
		 CAR = car;
		 
		Customer customer2  = customer;
		TimeSlot time2 =  new TimeSlot(LocalDateTime.of(2020, Month.JUNE,21,14,12,00),LocalDateTime.of(2022, Month.APRIL,21, 20, 00,00), 240);;
		Car car2 = new Car();
		car2.setId(45);
		car2.setAppointments(new ArrayList<Appointment>());
		car2.setCustomer(customer2);
		String dummyNote2 = "dummy Note2";
		List<Service> services2 = new ArrayList<Service>();
		services2.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer2, time2, car2, services2, dummyNote2);
		int id2 = customer2.hashCode()*time2.hashCode();
		
		APPOINTMENT_ID2 = id2;
		APPOINTMENT_STATUS2 = AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE2 = dummyNote2;
		CUSTOMER2 = customer2;
		TIME_SLOT2 = time2;
		CAR2 = car2;
		 
		List<Appointment> appointments = new ArrayList<Appointment>();
		
		try {
			appointments = service.getAppointmentsByCustomer(customer);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointments.get(0));
		assertEquals(appointments.get(0).getCar(), car);
		assertEquals(appointments.get(0).getCustomer(), customer);
		assertEquals(appointments.get(0).getId(), id);
		assertEquals(appointments.get(0).getTimeSlot(), time);
		assertEquals(appointments.get(0).getNote(), dummyNote);
		
		assertNotNull(appointments.get(1));
		assertEquals(appointments.get(1).getCar(), car2);
		assertEquals(appointments.get(1).getCustomer(), customer2);
		assertEquals(appointments.get(1).getId(), id2);
		assertEquals(appointments.get(1).getTimeSlot(), time2);
		assertEquals(appointments.get(1).getNote(), dummyNote2);	
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testGetAppointmentsById()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		Appointment appointment = service.createApp(customer, time, car, services1, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		APPOINTMENT_ID = customer.hashCode()*time.hashCode();;
		APPOINTMENT_STATUS= AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE = dummyNote;
		CUSTOMER = customer;
		TIME_SLOT = time;
		 CAR = car;
		
		try {
			appointment = service.getAppointmentById(id);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointment);
		assertEquals(appointment.getCar(), car);
		assertEquals(appointment.getCustomer(), customer);
		assertEquals(appointment.getId(), id);
		assertEquals(appointment.getTimeSlot(), time);
		assertEquals(appointment.getNote(), dummyNote);
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testGetAllAppointments()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer, time, car, services1, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		APPOINTMENT_ID = customer.hashCode()*time.hashCode();;
		APPOINTMENT_STATUS= AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE = dummyNote;
		CUSTOMER = customer;
		TIME_SLOT = time;
		 CAR = car;
		 
		Customer customer2  = new Customer("Dingo", 2222, "hello", 545, "Dingo@gmail.com","666545", "1234", "sugar");
		TimeSlot time2 =  new TimeSlot(LocalDateTime.of(2020, Month.JUNE,21,14,12,00),LocalDateTime.of(2022, Month.APRIL,21, 20, 00,00), 240);
		Car car2 = new Car();
		car2.setId(45);
		car2.setAppointments(new ArrayList<Appointment>());
		car2.setCustomer(customer2);
		String dummyNote2 = "dummy Note2";
		List<Service> services2 = new ArrayList<Service>();
		services2.add(new Service(Service.ServiceType.OilChange, 10));
		service.createApp(customer2, time2, car2, services2, dummyNote2);
		int id2 = customer2.hashCode()*time2.hashCode();
		
		APPOINTMENT_ID2 = id2;
		APPOINTMENT_STATUS2 = AppointmentStatus.InRepair;
		APPOINTMENT_NOTE2 = dummyNote2;
		CUSTOMER2 = customer2;
		TIME_SLOT2 = time2;
		CAR2 = car2;
		 
		List<Appointment> appointments = new ArrayList<Appointment>();
		
		try {
			appointments = service.getAllAppointments();
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointments.get(0));
		assertEquals(appointments.get(0).getCar(), car);
		assertEquals(appointments.get(0).getCustomer(), customer);
		assertEquals(appointments.get(0).getId(), id);
		assertEquals(appointments.get(0).getTimeSlot(), time);
		assertEquals(appointments.get(0).getNote(), dummyNote);
		
		assertNotNull(appointments.get(1));
		assertEquals(appointments.get(1).getCar(), car2);
		assertEquals(appointments.get(1).getCustomer(), customer2);
		assertEquals(appointments.get(1).getId(), id2);
		assertEquals(appointments.get(1).getTimeSlot(), time2);
		assertEquals(appointments.get(1).getNote(), dummyNote2);	
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testAddMechanic()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Mechanic mechanic = new Mechanic();
		mechanic.setAppointments(new ArrayList<Appointment>());
		mechanic.setId(12345);
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services2 = new ArrayList<Service>();
		services2.add(new Service(Service.ServiceType.OilChange, 10));
		Appointment appointment = service.createApp(customer, time, car, services2, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		try {
			service.addMechanic(appointment, mechanic);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointment);
		assertEquals(appointment.getMechanics().get(0), mechanic);
		assertEquals(appointment.getId(), id);
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testAddService()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Service s = new Service();
		s.setAppointments(new ArrayList<Appointment>());
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		Appointment appointment = service.createApp(customer, time, car, services1, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		try {
			service.addService(appointment, s);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointment);
		assertEquals(appointment.getServices().get(0), s);
		assertEquals(appointment.getId(), id);
		
	}
	
	@Test
	/**
	 * Verify that there is a car assigned to the appointment
	 */
	public void testAddImage()
	{
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Image image = new Image();
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		Appointment appointment = service.createApp(customer, time, car, services1, dummyNote);
		int id = customer.hashCode()*time.hashCode();
		
		try {
			service.addImage(appointment, image);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(appointment);
		assertEquals(appointment.getImages().get(0), image);
		assertEquals(appointment.getId(), id);
	}
	/*
	@Test
	public void testEditAppointment() {
		String error = null;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		TimeSlot time =  new TimeSlot(LocalDateTime.of(2021, Month.MARCH,21,14,12,00),LocalDateTime.of(2021, Month.MARCH,21, 20, 00,00), 240);
		Service s = new Service();
		s.setAppointments(new ArrayList<Appointment>());
		Car car = new Car();
		car.setId(100);
		car.setAppointments(new ArrayList<Appointment>());
		car.setCustomer(customer);
		String dummyNote = "dummy Note";
		int id = customer.hashCode()*time.hashCode();

		Car car2 = new Car();
		car2.setId(100);
		car2.setAppointments(new ArrayList<Appointment>());
		car2.setCustomer(customer);
		String dummyNote2 = "dummy Note2";
		List<Service> services1 = new ArrayList<Service>();
		services1.add(new Service(Service.ServiceType.OilChange, 10));
		Appointment appointment = service.createApp(customer, time, car, services1, dummyNote);

		
		APPOINTMENT_ID = customer.hashCode()*time.hashCode();;
		APPOINTMENT_STATUS= AppointmentStatus.AppointmentBooked;
		APPOINTMENT_NOTE = dummyNote;
		CUSTOMER = customer;
		TIME_SLOT = time;
		CAR = car2;
		
		try {
			appointment = service.editApp(customer, time, car2, dummyNote2);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
			
			
		}
		assertNotNull(appointment);
		assertEquals(appointment.getCar(), car2);
		assertEquals(appointment.getId(), id);
		assertEquals(appointment.getNote(), dummyNote2);
	}*/

	
}
