package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.lenient;
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
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;

@ExtendWith(MockitoExtension.class)
public class TestCarService {
	@Mock
	private CarRepository carDao;
	@Mock
	private CustomerRepository customerDao;

	@InjectMocks
	private CarService carService;
	private static int CAR_ID= 231424;
	private static CarType CAR_TYPE= CarType.SPORTS;
	private static int NUMBER_KILOMETERS= 143290;
	private static boolean WINTER_TIRES=false;
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();
	private static Customer CUSTOMER = new Customer("TestPerson", 2001, "123abc", 76523455,"TestPerson@gmail.com", "123456789","987654321", "123 Street Avenue");
	
	private static int CAR_ID2= 231424;
	private static CarType CAR_TYPE2= CarType.SPORTS;
	private static int NUMBER_KILOMETERS2= 143290;
	private static boolean WINTER_TIRES2=false;
	private static List<Appointment> APPOINTMENTS2 = new ArrayList<Appointment>();
	private static Customer CUSTOMER2 = new Customer("TestPerson", 2001, "123abc", 76523455,"TestPerson@gmail.com", "123456789","987654321", "123 Street Avenue");

	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(carDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CAR_ID)) {
				Car car = new Car();
				car.setId(CAR_ID);
				car.setCarType(CAR_TYPE);
				car.setNumOfKilometers(NUMBER_KILOMETERS);
				car.setWinterTires(WINTER_TIRES);
				car.setAppointments(APPOINTMENTS);
				car.setCustomer(CUSTOMER);
				return car;
			} else {
				return null;
			}
		});
		
		lenient().when(carDao.findByCustomer(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
			List<Car> cars = new ArrayList<Car>();
			Customer customer = invocation.getArgument(0);
			if (customer.getId() == CUSTOMER.getId()) {
				Car car = new Car();
				car.setId(CAR_ID);
				car.setCarType(CAR_TYPE);
				car.setNumOfKilometers(NUMBER_KILOMETERS);
				car.setWinterTires(WINTER_TIRES);
				car.setAppointments(APPOINTMENTS);
				car.setCustomer(CUSTOMER);
				cars.add(car);
			}
			if (customer.getId() == CUSTOMER2.getId()) {
				Car car = new Car();
				car.setId(CAR_ID2);
				car.setCarType(CAR_TYPE2);
				car.setNumOfKilometers(NUMBER_KILOMETERS2);
				car.setWinterTires(WINTER_TIRES2);
				car.setAppointments(APPOINTMENTS2);
				car.setCustomer(CUSTOMER2);
				cars.add(car);
			}
			return cars;
		});
		
		lenient().when(carDao.findByCarType(any(Car.CarType.class))).thenAnswer((InvocationOnMock invocation) -> {
			List<Car> cars = new ArrayList<Car>();
			CarType carType = invocation.getArgument(0);
			if (carType.equals(CAR_TYPE)) {
				Car car = new Car();
				car.setId(CAR_ID);
				car.setCarType(CAR_TYPE);
				car.setNumOfKilometers(NUMBER_KILOMETERS);
				car.setWinterTires(WINTER_TIRES);
				car.setAppointments(APPOINTMENTS);
				car.setCustomer(CUSTOMER);
				cars.add(car);
			}
			if (carType.equals(CAR_TYPE2)) {
				Car car = new Car();
				car.setId(CAR_ID2);
				car.setCarType(CAR_TYPE2);
				car.setNumOfKilometers(NUMBER_KILOMETERS2);
				car.setWinterTires(WINTER_TIRES2);
				car.setAppointments(APPOINTMENTS2);
				car.setCustomer(CUSTOMER2);
				cars.add(car);
			}
			return cars;
		});
		
	lenient().when(carDao.findByWinterTires(anyBoolean())).thenAnswer((InvocationOnMock invocation) -> {
			List<Car> cars = new ArrayList<Car>();
			Boolean winterTires = invocation.getArgument(0);
			if (winterTires.equals(WINTER_TIRES)) {
				Car car = new Car();
				car.setId(CAR_ID);
				car.setCarType(CAR_TYPE);
				car.setNumOfKilometers(NUMBER_KILOMETERS);
				car.setWinterTires(WINTER_TIRES);
				car.setAppointments(APPOINTMENTS);
				car.setCustomer(CUSTOMER);
				cars.add(car);
			}
			if (winterTires.equals(WINTER_TIRES2)) {
				Car car = new Car();
				car.setId(CAR_ID2);
				car.setCarType(CAR_TYPE2);
				car.setNumOfKilometers(NUMBER_KILOMETERS2);
				car.setWinterTires(WINTER_TIRES2);
				car.setAppointments(APPOINTMENTS2);
				car.setCustomer(CUSTOMER2);
				cars.add(car);
			}
			return cars;
		});
		
	
	Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
		return invocation.getArgument(0);
	};
	
	lenient().when(carDao.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);
	lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
		
	}
	
	
	@Test
	/**
	 * Test the creation of a car object 
	 */
	public void testCreateCar()
	{
		assertEquals(0, carService.getAllCars().size());
		CarType type = CarType.CONVERTIBLE;
		boolean winterTires = true;
		int numOfKm = 53467;
		List<Appointment> appointment = new ArrayList<Appointment>();
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com", "123456", "678954", "123 avenue street");
		Car car = null;
		try
		{
			car = carService.createCar(type, winterTires, numOfKm,  customer);
		}catch(IllegalArgumentException e)
		{
			fail();
		}
		assertNotNull(car);
		assertEquals(type, car.getCarType());
		assertEquals(winterTires, car.getWinterTires());
		assertEquals(numOfKm,car.getNumOfKilometers());
		assertEquals(appointment, car.getAppointments());
		assertEquals(customer, car.getCustomer());
	}

	@Test
	/**
	 * Verifies that the car type is not null
	 */
	public void testCreateCarTypeNull()
	{
		String error = null;
		CarType type = null;
		boolean winterTires = false;
		int numOfKm = 53467;
		Customer customer  = new Customer("Marcus", 012123, "password", 6789876, "Marcus@gmail.com","123456", "678954", "123 avenue street");
		Car car = null;
		
		try {
			car = carService.createCar(type, winterTires, numOfKm,  customer);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(car);
		assertEquals("Car Type cannot be null", error);
	}
	
	@Test
	/**
	 * Verifies that a customer object is associated to a car
	 */
	public void testCreateCustomerNull()
	{
		String error = null;
		CarType type = CarType.TRUCK;
		boolean winterTires = false;
		int numOfKm = 455679;
		Customer customer  = null;
		List<Appointment> appointments = new ArrayList<Appointment>();
		Car car = null;
		
		try {
			car = carService.createCar(type, winterTires, numOfKm, customer);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNull(car);
		assertEquals("Customer cannot be null", error);
	}
	
	@Test
	/**
	 * Verifies that a customer object is associated to a car
	 */
	public void testGetCarById()
	{
		String error = null;
		CarType type = CarType.TRUCK;
		boolean winterTires = false;
		int numOfKm = 455679;
		Customer customer  = new Customer();
		customer.setCars(new ArrayList<Car>());
		List<Appointment> appointments = new ArrayList<Appointment>();
		Car car = carService.createCar(type, winterTires, numOfKm, customer);
		int id = Integer.valueOf(numOfKm).hashCode()*customer.hashCode();
		
		CAR_ID= id;
		CAR_TYPE= type;
		NUMBER_KILOMETERS= numOfKm;
		WINTER_TIRES= winterTires;
		APPOINTMENTS = appointments;
		CUSTOMER = customer;
		
		try {
			car = carService.getCarById(id);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(car);
		assertEquals(car.getId(), id);
	}
	
	@Test
	/**
	 * Verifies that a customer object is associated to a car
	 */
	public void testGetCarsByCustomer()
	{
		String error = null;
		CarType type = CarType.TRUCK;
		boolean winterTires = false;
		int numOfKm = 455679;
		Customer customer  = new Customer();
		customer.setCars(new ArrayList<Car>());
		customer.setId(100);
		List<Appointment> appointments = new ArrayList<Appointment>();
		Car car = carService.createCar(type, winterTires, numOfKm, customer);
		int id = Integer.valueOf(numOfKm).hashCode()*customer.hashCode();
		
		CAR_ID= id;
		CAR_TYPE= type;
		NUMBER_KILOMETERS= numOfKm;
		WINTER_TIRES= winterTires;
		APPOINTMENTS = appointments;
		CUSTOMER = customer;
		
		CarType type2 = CarType.SUV;
		boolean winterTires2 = true;
		int numOfKm2 = 4556;
		Customer customer2  = customer;
		List<Appointment> appointments2 = new ArrayList<Appointment>();
		Car car2 = carService.createCar(type2, winterTires2, numOfKm2, customer2);
		int id2 = Integer.valueOf(numOfKm2).hashCode()*customer2.hashCode();
		
		CAR_ID2 = id2;
		CAR_TYPE2 = type2;
		NUMBER_KILOMETERS2 = numOfKm2;
		WINTER_TIRES2 = winterTires2;
		APPOINTMENTS2 = appointments2;
		CUSTOMER2 = customer2;
		List<Car> cars = new ArrayList<Car>();
		
		try {
			cars = carService.getCarsByCustomer(customer);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(cars);
		assertEquals(cars.get(0).getId(), id);
		assertEquals(cars.get(0).getCustomer(), customer);
		assertEquals(cars.get(1).getId(), id2);
		assertEquals(cars.get(1).getCustomer(), customer);
	}
	
	@Test
	/**
	 * Verifies that a customer object is associated to a car
	 */
	public void testGetCarsByCarType()
	{
		String error = null;
		CarType type = CarType.TRUCK;
		boolean winterTires = false;
		int numOfKm = 455679;
		Customer customer  = new Customer();
		customer.setCars(new ArrayList<Car>());
		customer.setId(100);
		List<Appointment> appointments = new ArrayList<Appointment>();
		Car car = carService.createCar(type, winterTires, numOfKm, customer);
		int id = Integer.valueOf(numOfKm).hashCode()*customer.hashCode();
		
		CAR_ID= id;
		CAR_TYPE= type;
		NUMBER_KILOMETERS= numOfKm;
		WINTER_TIRES= winterTires;
		APPOINTMENTS = appointments;
		CUSTOMER = customer;
		
		CarType type2 = CarType.TRUCK;
		boolean winterTires2 = true;
		int numOfKm2 = 4556;
		Customer customer2  = new Customer();
		customer2.setCars(new ArrayList<Car>());
		customer2.setId(50);
		List<Appointment> appointments2 = new ArrayList<Appointment>();
		Car car2 = carService.createCar(type2, winterTires2, numOfKm2, customer2);
		int id2 = Integer.valueOf(numOfKm2).hashCode()*customer2.hashCode();
		
		CAR_ID2 = id2;
		CAR_TYPE2 = type2;
		NUMBER_KILOMETERS2 = numOfKm2;
		WINTER_TIRES2 = winterTires2;
		APPOINTMENTS2 = appointments2;
		CUSTOMER2 = customer2;
		List<Car> cars = new ArrayList<Car>();
		
		try {
			cars = carService.getCarsByCarType(CarType.TRUCK);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(cars);
		assertEquals(cars.get(0).getId(), id);
		assertEquals(cars.get(0).getCarType(), type);
		assertEquals(cars.get(1).getId(), id2);
		assertEquals(cars.get(1).getCarType(), type2);
	}
	
	@Test
	/**
	 * Verifies that a customer object is associated to a car
	 */
	public void testGetCarsBywinterTires()
	{
		String error = null;
		CarType type = CarType.TRUCK;
		boolean winterTires = true;
		int numOfKm = 455679;
		Customer customer  = new Customer();
		customer.setCars(new ArrayList<Car>());
		customer.setId(100);
		List<Appointment> appointments = new ArrayList<Appointment>();
		Car car = carService.createCar(type, winterTires, numOfKm, customer);
		int id = Integer.valueOf(numOfKm).hashCode()*customer.hashCode();
		
		CAR_ID= id;
		CAR_TYPE= type;
		NUMBER_KILOMETERS= numOfKm;
		WINTER_TIRES= winterTires;
		APPOINTMENTS = appointments;
		CUSTOMER = customer;
		
		CarType type2 = CarType.TRUCK;
		boolean winterTires2 = true;
		int numOfKm2 = 4556;
		Customer customer2  = new Customer();
		customer2.setCars(new ArrayList<Car>());
		customer2.setId(50);
		List<Appointment> appointments2 = new ArrayList<Appointment>();
		Car car2 = carService.createCar(type2, winterTires2, numOfKm2, customer2);
		int id2 = Integer.valueOf(numOfKm2).hashCode()*customer2.hashCode();
		
		CAR_ID2 = id2;
		CAR_TYPE2 = type2;
		NUMBER_KILOMETERS2 = numOfKm2;
		WINTER_TIRES2 = winterTires2;
		APPOINTMENTS2 = appointments2;
		CUSTOMER2 = customer2;
		List<Car> cars = new ArrayList<Car>();
		
		try {
			cars = carService.getCarsByWinterTires(true);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(cars);
		assertEquals(cars.get(0).getId(), id);
		assertEquals(cars.get(0).getWinterTires(), winterTires);
		assertEquals(cars.get(1).getId(), id2);
		assertEquals(cars.get(1).getWinterTires(), winterTires2);
	}
	
}
