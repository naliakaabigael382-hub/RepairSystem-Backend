package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.Calendar;
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

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {
	@Mock
	private CustomerRepository customerDao;

	@InjectMocks
	private CustomerService customerService;

	private static String CUSTOMER_KEY = "TestCustomer";
	private static int CUSTOMER_ID = 1234;
	private static String PASSWORD = "customer";
	private static int PHONE = 123000000;
	private static String EMAIL = "customer@repairsystem.com";
	private static Calendar LAST_DATE = Calendar.getInstance();
	private static String DEBIT = "4321";
	private static String CREDIT = "1234";
	private static String ADD = "456 Swan Lake";
	private static List<Car> CARS = new ArrayList<Car>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();	
	
	private static String CUSTOMER2_NAME = "customer2";
	private static int CUSTOMER_ID2=5678;
	private static String PASSWORD2="customer2";
	private static int PHONE2 = 967854321;
	private static String EMAIL2 = "customer2@gmail.com";
	private static Calendar LAST_DATE2=Calendar.getInstance();
	private static String DEBIT2="09985641";
	private static String CREDIT2="45632145";
	private static String ADD2 = "911 Boulevard HELPME";
	private static List<Car> CARS2 = new ArrayList<Car>();
	private static List<Appointment> APPOINTMENTS2= new ArrayList<Appointment>();	
	
	

	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(customerDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			List<Customer> customers = new ArrayList<Customer>();
			String name = invocation.getArgument(0);
			if(name.equals(CUSTOMER_KEY)) {
				Customer customer = new Customer();
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);	
				customers.add(customer);
				return customers;
			
			} else {
				return null;
			}
		});


		lenient().when(customerDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			Customer customer = new Customer();

			if (invocation.getArgument(0).equals(CUSTOMER_ID)) {
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				return customer;
			} else {
				return null;
			}
		});

		lenient().when(customerDao.findByPhone(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			Customer customer = new Customer();

			if (invocation.getArgument(0).equals(PHONE)) {
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				return customer;
			} else {
				return null;
			}
		});

		lenient().when(customerDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			Customer customer = new Customer();

			if (invocation.getArgument(0).equals(EMAIL)) {
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				return customer;
			} else {
				return null;
			}
		});
		
		lenient().when(customerDao.findByAddress(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			List<Customer> customers = new ArrayList<Customer>();
			String address = invocation.getArgument(0);
			if (address.equals(ADD)) {
				Customer customer = new Customer();
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);
				customers.add(customer);
				return customers;
			} else {
				return null;
			}
		});
		
		
		lenient().when(customerDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
				
			List<Customer> customers = new ArrayList<Customer>();
				Customer customer = new Customer();
				customer.setName(CUSTOMER_KEY);
				customer.setEmail(EMAIL);
				customer.setId(CUSTOMER_ID);
				customer.setPassword(PASSWORD);
				customer.setPhone(PHONE);
				customer.setAddress(ADD);
				customer.setAppointments(APPOINTMENTS);
				customer.setCars(CARS);
				customer.setCreditHash(CREDIT);
				customer.setDebitHash(DEBIT);
				customer.setLastActive(LAST_DATE);	
				customers.add(customer);
			
				Customer customer2 = new Customer();
				customer2.setName(CUSTOMER2_NAME);
				customer2.setEmail(EMAIL2);
				customer2.setId(CUSTOMER_ID2);
				customer2.setPassword(PASSWORD2);
				customer2.setPhone(PHONE2);
				customer2.setAddress(ADD2);
				customer2.setAppointments(APPOINTMENTS2);
				customer2.setCars(CARS2);
				customer2.setCreditHash(CREDIT2);
				customer2.setDebitHash(DEBIT2);
				customer2.setLastActive(LAST_DATE2);	
				
				customers.add(customer);
				customers.add(customer2);
				return customers;
		});

		

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateCustomer() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		int customerId = aEmail.hashCode();

		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail,  credit, debit, address);	
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(customer);
		assertEquals(customerId, customer.getId());
	}
	@Test
	public void testCreateNameNull() {

		Customer customer = null;
		String name = null;
		String aPassword = "123412";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		
		int customerId = aEmail.hashCode();
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID = customerId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer name cannot be empty!", error);
	}
	@Test
	public void testCreateNameEmpty() {

		Customer customer = null;
		String name = "";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		
		int customerId = aEmail.hashCode();
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID = customerId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer name cannot be empty!", error);
	}
	@Test
	public void testCreatePasswardNull() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = null;
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		
		int customerId = aEmail.hashCode();
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID = customerId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer password cannot be empty!", error);
	}
	@Test
	public void testCreatePasswardEmpty() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		int customerId = aEmail.hashCode();
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID = customerId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer password cannot be empty!", error);
	}
	@Test
	public void testCreateEmailNull() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = null;
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer email cannot be empty!", error);
	}
	@Test
	public void testCreateEmailEmpty() {

		Customer customer = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		
		int customerId = aEmail.hashCode();
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID = customerId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		try {
			customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer email cannot be empty!", error);
	}
	
	@Test 
	public void testResetPassword()
	{
		
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "oscar@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String newPassword = "newPassword";
		String error = "";
		Customer customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
	
		int customerId = aEmail.hashCode();
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID = customerId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		
		try {
			
			customerService.resetPassword(customer, newPassword);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(newPassword, customer.getPassword());
	}
	
	@Test 
	public void testEditCustomer()
	{
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "oscar@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		String error = null;
		Customer customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);
		int id = aEmail.hashCode();
		
		String newName = "David";
		String newPassword = "poopy";
		String newPhone = "4321";
		String newEmail = "goodbye";
		String newCredit ="0987766";
		String newDebit = "1234566";
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID = id;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		
		
		try {
			customerService.updateAllCredentials(aEmail, newName, newPassword, newPhone, newCredit, newDebit, address);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(customer);
		assertEquals(customer.getPassword(), newPassword);
		assertEquals(customer.getPhone(), Integer.parseInt(newPhone));
		assertEquals(customer.getDebitHash(), newDebit);
		assertEquals(customer.getCreditHash(), newCredit);
		assertEquals(customer.getAddress(), address);
	}
	
	@Test
	public void testGetCustomersByName()
	{
		String error = null;

		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "ocascar@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
		int customerId = aEmail.hashCode();
		//Customer customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID = customerId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		
		List<Customer> customers = new ArrayList<Customer>();
		
		try {
			customers = customerService.getCustomersByName(name);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(customers.get(0));
		assertEquals(customers.get(0).getName(), name);
		assertEquals(customers.get(0).getPassword(),aPassword);
		assertEquals(customers.get(0).getId(), customerId);
		assertEquals(customers.get(0).getEmail(), aEmail);
		assertEquals(customers.get(0).getCreditHash(), credit);
		assertEquals(customers.get(0).getDebitHash(), debit);
		assertEquals(customers.get(0).getAddress(), address);
	}
	
	
	@Test
	public void testGetCustomerById()
	{
		String error = null;
		Customer customer = new Customer();
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "ocascar@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
	   // customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID =aEmail.hashCode();
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		
		try {
			customer  = customerService.getCustomerById(CUSTOMER_ID);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(customer);
		assertEquals(customer.getName(), name);
		assertEquals(customer.getPassword(), aPassword);
		assertEquals(customer.getEmail(), aEmail);
		assertEquals(customer.getDebitHash(), debit);
		assertEquals(customer.getCreditHash(), credit);
		assertEquals(customer.getDebitHash(), debit);
		assertEquals(customer.getAddress(), address);
	}
	
	@Test
	public void testGetCustomerByPhone()
	{
		String error = null;
		Customer customer = new Customer();
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "ocascar@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
	   // customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID =aEmail.hashCode();
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		
		try {
			customer  = customerService.getCustomerByNumber(PHONE);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(customer);
		assertEquals(customer.getName(), name);
		assertEquals(customer.getPassword(), aPassword);
		assertEquals(customer.getEmail(), aEmail);
		assertEquals(customer.getDebitHash(), debit);
		assertEquals(customer.getCreditHash(), credit);
		assertEquals(customer.getDebitHash(), debit);
		assertEquals(customer.getAddress(), address);
	
	}
	
	
	@Test
	public void testGetCustomerByEmail()
	{
		String error = null;
		Customer customer = new Customer();
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "ocascar@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 avenue street";
	   // customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID =aEmail.hashCode();
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		
		try {
			customer  = customerService.getCustomerByEmail(EMAIL);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(customer);
		assertEquals(customer.getName(), name);
		assertEquals(customer.getPassword(), aPassword);
		assertEquals(customer.getEmail(), aEmail);
		assertEquals(customer.getDebitHash(), debit);
		assertEquals(customer.getCreditHash(), credit);
		assertEquals(customer.getDebitHash(), debit);
		assertEquals(customer.getAddress(), address);
	
	}
	
	
	@Test
	public void testGetCustomerByAddress()
	{
		String error = null;
		
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "OscarWild@gmail.com";
		String credit = "1234566";
		String debit = "0987766";
		String address = "123 Wilfred Street";
		Customer customer = customerService.createCustomer(name, aPassword, aPhone, aEmail, credit, debit, address);	
		int customerId = aEmail.hashCode();
		
		CUSTOMER_KEY = name;
		CUSTOMER_ID =customerId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		LAST_DATE = Calendar.getInstance();
		DEBIT = debit;
		CREDIT = credit;
		ADD = address;
		
		String name2 = "Oscar";
		String aPassword2 = "password2";
		int aPhone2 = 3245678;
		String aEmail2 = "wild@gmail.com";
		String credit2 = "0987654";
		String debit2= "3214567";
		String address2 = "123 Wilfred Street";
		Customer customer2 = customerService.createCustomer(name2, aPassword2, aPhone2, aEmail2, credit2, debit2, address2);
		
		CUSTOMER2_NAME = name2;
		CUSTOMER_ID2 =aEmail.hashCode();
		PASSWORD2 = aPassword2;
		PHONE2 = aPhone2;
		EMAIL2 = aEmail2;
		LAST_DATE2 = Calendar.getInstance();
		DEBIT2 = debit2;
		CREDIT2 = credit2;
		ADD2 = address2;
		
		List<Customer> customers = new ArrayList<Customer>();
		
		try {
			customers = customerService.getCustomersByAddress(address);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}
		
		assertNotNull(customers.get(0));
		assertEquals(customers.get(0).getName(), name);
		assertEquals(customers.get(0).getPassword(), aPassword);
		assertEquals(customers.get(0).getEmail(), aEmail);
		assertEquals(customers.get(0).getCreditHash(), credit);
		assertEquals(customers.get(0).getDebitHash(), debit);
		assertEquals(customers.get(0).getAddress(), address);
		
	/*	assertNotNull(customers.get(1));
		assertEquals(customers.get(1), customer2);
		assertEquals(customers.get(1).getName(), name);
		assertEquals(customers.get(1).getPassword(), PASSWORD2);
		assertEquals(customers.get(1).getId(), CUSTOMER_ID2);
		assertEquals(customers.get(1).getEmail(), EMAIL2);
		assertEquals(customers.get(1).getCreditHash(), CREDIT2);
		assertEquals(customers.get(1).getDebitHash(), DEBIT2);
		assertEquals(customers.get(1).getAddress(), ADD2);*/
	
	}
}
