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
public class TestMechanicService {
	@Mock
	private MechanicRepository mechanicDao;

	@InjectMocks
	private MechanicService mechanicService;

	private static String MECHANIC_KEY = "TestMechanic";
	private static int MECHANIC_ID = 1234;
	private static String PASSWORD = "mechanic";
	private static int PHONE = 123000000;
	private static String EMAIL = "mechanic@repairsystem.com";
	private static RepairSystem REPAIR_SYSTEM = new RepairSystem();
	private static List<Service> ALL_CAPABILITIES = new ArrayList<Service>();
	private static List<TimeSlot> TIME_SLOTS = new ArrayList<TimeSlot>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();


	private static String MECHANIC_KEY2 = "TestMechanic2";
	private static int MECHANIC_ID2 = 12334;
	private static String PASSWORD2 = "mec2hanic";
	private static int PHONE2 = 124000000;
	private static String EMAIL2 = "mechanic2@repairsystem.com";
	private static RepairSystem REPAIR_SYSTEM2 = new RepairSystem();
	private static List<Service> ALL_CAPABILITIES2 = new ArrayList<Service>();
	private static List<TimeSlot> TIME_SLOTS2 = new ArrayList<TimeSlot>();
	private static List<Appointment> APPOINTMENTS2 = new ArrayList<Appointment>();

	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(mechanicDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			Mechanic mechanic = new Mechanic();
			List<Mechanic> mechanics = new ArrayList<Mechanic>();

			if(invocation.getArgument(0).equals(MECHANIC_KEY)) {
				mechanic.setName(MECHANIC_KEY);
				mechanic.setAppointments(APPOINTMENTS);
				mechanic.setEmail(EMAIL);
				mechanic.setPassword(PASSWORD);
				mechanic.setPhone(PHONE);
				mechanic.setServices(ALL_CAPABILITIES);
				mechanic.setTimeSlots(TIME_SLOTS);
				mechanic.setId(MECHANIC_ID);

				mechanics.add(mechanic);
				return mechanics;
			} else {
				return null;
			}
		});


		lenient().when(mechanicDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			Mechanic mechanic = new Mechanic();

			if (invocation.getArgument(0).equals(MECHANIC_ID)) {
				mechanic.setName(MECHANIC_KEY);
				mechanic.setAppointments(APPOINTMENTS);
				mechanic.setEmail(EMAIL);
				mechanic.setPassword(PASSWORD);
				mechanic.setPhone(PHONE);
				mechanic.setServices(ALL_CAPABILITIES);
				mechanic.setTimeSlots(TIME_SLOTS);
				mechanic.setId(MECHANIC_ID);

				return mechanic;
			} else {
				return null;
			}
		});

		lenient().when(mechanicDao.findByPhone(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			Mechanic mechanic = new Mechanic();

			if (invocation.getArgument(0).equals(PHONE)) {
				mechanic.setName(MECHANIC_KEY);
				mechanic.setAppointments(APPOINTMENTS);
				mechanic.setEmail(EMAIL);
				mechanic.setPassword(PASSWORD);
				mechanic.setPhone(PHONE);
				mechanic.setServices(ALL_CAPABILITIES);
				mechanic.setTimeSlots(TIME_SLOTS);
				mechanic.setId(MECHANIC_ID);

				return mechanic;
			} else {
				return null;
			}
		});

		lenient().when(mechanicDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			Mechanic mechanic = new Mechanic();

			if (invocation.getArgument(0).equals(EMAIL)) {
				mechanic.setName(MECHANIC_KEY);
				mechanic.setAppointments(APPOINTMENTS);
				mechanic.setEmail(EMAIL);
				mechanic.setPassword(PASSWORD);
				mechanic.setPhone(PHONE);
				mechanic.setServices(ALL_CAPABILITIES);
				mechanic.setTimeSlots(TIME_SLOTS);
				mechanic.setId(MECHANIC_ID);

				return mechanic;
			} else {
				return null;
			}
		});



		lenient().when(mechanicDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Mechanic> mechanics = new ArrayList<Mechanic>();
			Mechanic mechanic = new Mechanic();
			mechanic.setEmail(EMAIL);
			mechanic.setPassword(PASSWORD);
			mechanic.setPhone(PHONE);
			mechanic.setAppointments(APPOINTMENTS);
			mechanic.setName(MECHANIC_KEY);
			mechanic.setServices(ALL_CAPABILITIES);
			mechanic.setTimeSlots(TIME_SLOTS);
			mechanic.setId(MECHANIC_ID);

			Mechanic mechanic2 = new Mechanic();
			mechanic2.setEmail(EMAIL2);
			mechanic2.setPassword(PASSWORD2);
			mechanic2.setPhone(PHONE2);
			mechanic2.setAppointments(APPOINTMENTS2);
			mechanic2.setName(MECHANIC_KEY2);
			mechanic2.setServices(ALL_CAPABILITIES2);
			mechanic2.setTimeSlots(TIME_SLOTS2);
			mechanic2.setId(MECHANIC_ID2);

			mechanics.add(mechanic);
			mechanics.add(mechanic2);
			return mechanics;
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(mechanicDao.save(any(Mechanic.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	/**
	 * Test the creation mechanic
	 */
	public void testCreateMechanicNull() {

		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		List<Service> allCapabilities = new ArrayList<Service>();
		int mechanicId = aEmail.hashCode();

		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, allCapabilities);		
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(mechanic);
		assertEquals(mechanicId, mechanic.getId());
	}



	@Test
	/**
	 * Test if the name is null
	 */
	public void testCreateNameNull() {
		Mechanic mechanic = null;
		String name = null;
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, allCapabilities);			
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic name cannot be empty!", error);
	}

	@Test
	/**
	 * check if the name is empty
	 */
	public void testCreateNameSpace() {
		Mechanic mechanic = null;
		String name = " ";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, allCapabilities);			
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic name cannot be empty!", error);
	}

	@Test
	public void testCreatePasswordNull() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = null;
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, allCapabilities);			
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic password cannot be empty!", error);
	}

	@Test
	public void testCreatePasswordEmpty() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, allCapabilities);			
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic password cannot be empty!", error);
	}

	@Test
	public void testCreateEmailNull() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = null;
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, allCapabilities);			
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic email cannot be empty!", error);
	}

	@Test
	/**
	 * Test to check if email is empty
	 */
	public void testCreateEmailEmpty() {
		Mechanic mechanic = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "";
		RepairSystem system = new RepairSystem();
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		try {
			mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, allCapabilities);			
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(mechanic);
		assertEquals("Mechanic email cannot be empty!", error);
	}


	@Test
	public void testGetMechanicByName() {
		Mechanic mechanic = new Mechanic();
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		List<Service> allCapabilities = new ArrayList<Service>();
		int mechanicId = aEmail.hashCode();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		List<Mechanic> mechanics = new ArrayList<Mechanic>();

		String error = null;

		MECHANIC_KEY = name;
		MECHANIC_ID = mechanicId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		ALL_CAPABILITIES = allCapabilities;
		TIME_SLOTS = timeSlots;
		APPOINTMENTS = appointments;

		try {
			mechanics = mechanicService.getMechanicsByName(name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(mechanic);
		assertEquals(mechanics.get(0).getEmail(), aEmail);
		assertEquals(mechanics.get(0).getPhone(), aPhone);
		assertEquals(mechanics.get(0).getName(), name);
		assertEquals(mechanics.get(0).getPassword(), aPassword);
		assertEquals(mechanics.get(0).getId(), mechanicId);
		assertEquals(mechanics.get(0).getAppointments(), appointments);
		assertEquals(mechanics.get(0).getServices(), allCapabilities);
		assertEquals(mechanics.get(0).getTimeSlots(), timeSlots);
	}


	@Test
	public void testGetMechanicByPhone() {
		Mechanic mechanic = new Mechanic();
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		List<Service> allCapabilities = new ArrayList<Service>();
		int mechanicId = aEmail.hashCode();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		String error = null;

		MECHANIC_KEY = name;
		MECHANIC_ID = mechanicId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		ALL_CAPABILITIES = allCapabilities;
		TIME_SLOTS = timeSlots;
		APPOINTMENTS = appointments;

		try {
			mechanic = mechanicService.getMechanicByNumber(aPhone);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(mechanic);
		assertEquals(mechanic.getEmail(), aEmail);
		assertEquals(mechanic.getPhone(), aPhone);
		assertEquals(mechanic.getName(), name);
		assertEquals(mechanic.getPassword(), aPassword);
		assertEquals(mechanic.getId(), mechanicId);
		assertEquals(mechanic.getAppointments(), appointments);
		assertEquals(mechanic.getServices(), allCapabilities);
		assertEquals(mechanic.getTimeSlots(), timeSlots);
	}


	@Test
	public void testGetAllMechanics() {
		Mechanic mechanic = new Mechanic();
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		List<Service> allCapabilities = new ArrayList<Service>();
		int mechanicId = aEmail.hashCode();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		List<Appointment> appointments = new ArrayList<Appointment>();

		Mechanic mechanic2 = new Mechanic();
		String name2 = "Oscar";
		String aPassword2 = "123412";
		int aPhone2 = 123456789;
		String aEmail2 = "email@repairsystem.com";
		List<Service> allCapabilities2 = new ArrayList<Service>();
		int mechanicId2 = aEmail2.hashCode();
		List<TimeSlot> timeSlots2 = new ArrayList<TimeSlot>();
		List<Appointment> appointments2 = new ArrayList<Appointment>();

		String error = null;

		List<Mechanic> mechanics = new ArrayList<Mechanic>();

		MECHANIC_KEY = name;
		MECHANIC_ID = mechanicId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		ALL_CAPABILITIES = allCapabilities;
		TIME_SLOTS = timeSlots;
		APPOINTMENTS = appointments;


		MECHANIC_KEY2 = name2;
		MECHANIC_ID2 = mechanicId2;
		PASSWORD2 = aPassword2;
		PHONE2 = aPhone2;
		EMAIL2 = aEmail2;
		ALL_CAPABILITIES2 = allCapabilities2;
		TIME_SLOTS2 = timeSlots2;
		APPOINTMENTS2 = appointments2;

		try {
			mechanics = mechanicService.getAllMechanics();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(mechanics.get(0));
		assertEquals(mechanics.get(0).getEmail(), aEmail);
		assertEquals(mechanics.get(0).getPhone(), aPhone);
		assertEquals(mechanics.get(0).getName(), name);
		assertEquals(mechanics.get(0).getPassword(), aPassword);
		assertEquals(mechanics.get(0).getId(), mechanicId);
		assertEquals(mechanics.get(0).getServices(), allCapabilities);
		assertEquals(mechanics.get(0).getAppointments(), appointments);


		assertNotNull(mechanics.get(1));
		assertEquals(mechanics.get(1).getEmail(), aEmail2);
		assertEquals(mechanics.get(1).getPhone(), aPhone2);
		assertEquals(mechanics.get(1).getName(), name2);
		assertEquals(mechanics.get(1).getPassword(), aPassword2);
		assertEquals(mechanics.get(1).getId(), mechanicId2);
		assertEquals(mechanics.get(1).getServices(), allCapabilities2);
		assertEquals(mechanics.get(1).getAppointments(), appointments2);

	}

	@Test
	public void testGetMechanicById() {
		Mechanic mechanic = new Mechanic();
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		List<Service> allCapabilities = new ArrayList<Service>();
		int mechanicId = aEmail.hashCode();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		String error = null;

		MECHANIC_KEY = name;
		MECHANIC_ID = mechanicId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		ALL_CAPABILITIES = allCapabilities;
		TIME_SLOTS = timeSlots;
		APPOINTMENTS = appointments;
		try {
			mechanic = mechanicService.getMechanicById(mechanicId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(mechanic);
		assertEquals(mechanic.getEmail(), aEmail);
		assertEquals(mechanic.getPhone(), aPhone);
		assertEquals(mechanic.getName(), name);
		assertEquals(mechanic.getPassword(), aPassword);
		assertEquals(mechanic.getId(), mechanicId);
		assertEquals(mechanic.getAppointments(), appointments);
		assertEquals(mechanic.getServices(), allCapabilities);
		assertEquals(mechanic.getTimeSlots(), timeSlots);
	}

	@Test
	public void testGetMechanicByEmail() {
		Mechanic mechanic = new Mechanic();
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		List<Service> allCapabilities = new ArrayList<Service>();
		int mechanicId = aEmail.hashCode();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		String error = null;

		MECHANIC_KEY = name;
		MECHANIC_ID = mechanicId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		ALL_CAPABILITIES = allCapabilities;
		TIME_SLOTS = timeSlots;
		APPOINTMENTS = appointments;
		try {
			mechanic = mechanicService.getMechanicByEmail(aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(mechanic);
		assertEquals(mechanic.getEmail(), aEmail);
		assertEquals(mechanic.getPhone(), aPhone);
		assertEquals(mechanic.getName(), name);
		assertEquals(mechanic.getPassword(), aPassword);
		assertEquals(mechanic.getId(), mechanicId);
		assertEquals(mechanic.getAppointments(), appointments);
		assertEquals(mechanic.getServices(), allCapabilities);
		assertEquals(mechanic.getTimeSlots(), timeSlots);
	}

	@Test
	public void testEditMechanic() {
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		List<Service> allCapabilities = new ArrayList<Service>();
		String error = null;
		Mechanic mechanic = mechanicService.createMechanic(name, aPassword, aPhone, aEmail, allCapabilities);
		int mechanicId = aEmail.hashCode();
		
		MECHANIC_KEY = name;
		MECHANIC_ID = mechanicId;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		ALL_CAPABILITIES = allCapabilities;

		String newName = "Oscar2";
		String newPassword = "1234122";
		String newPhone = "123459789";
		String newEmail = "email2@repairsystem.com";
		

		try {
			mechanic = mechanicService.editMechanic(aEmail, newName, newPassword, newPhone); 
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(mechanic);
		assertEquals(mechanic.getPhone(), Integer.parseInt(newPhone));
		assertEquals(mechanic.getName(), newName);
		assertEquals(mechanic.getPassword(), newPassword);
		assertEquals(mechanic.getId(), mechanicId);
	}


}
