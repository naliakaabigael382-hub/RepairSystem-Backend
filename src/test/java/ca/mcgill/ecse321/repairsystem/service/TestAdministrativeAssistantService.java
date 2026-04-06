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
public class TestAdministrativeAssistantService {
	@Mock
	private AdministrativeAssistantRepository administrativeAssistantDao;

	@InjectMocks
	private AdministrativeAssistantService administrativeAssistantService;

	private static String ADMINISTRATIVE_ASSISTANT_KEY = "TestAdministrativeAssistant";
	private static int ADMINISTRATIVE_ASSISTANT_ID = 1234;
	private static String PASSWORD = "administrativeAssistant";
	private static int PHONE = 123000000;
	private static String EMAIL = "administrativeAssistant@repairsystem.com";
	
	private static String ADMINISTRATIVE_ASSISTANT_KEY2 = "TestAdministrativeAssistant";
	private static int ADMINISTRATIVE_ASSISTANT_ID2 = 1234;
	private static String PASSWORD2 = "administrativeAssistant";
	private static int PHONE2 = 123000000;
	private static String EMAIL2 = "administrativeAssistant@repairsystem.com";
	
	
	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(administrativeAssistantDao.findByName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			List<AdministrativeAssistant> administrativeAssistants = new ArrayList<AdministrativeAssistant>();
			if(invocation.getArgument(0).equals(ADMINISTRATIVE_ASSISTANT_KEY)) {
				AdministrativeAssistant admin = new AdministrativeAssistant();
				admin.setName(ADMINISTRATIVE_ASSISTANT_KEY);
				admin.setEmail(EMAIL);
				admin.setId(ADMINISTRATIVE_ASSISTANT_ID);
				admin.setPassword(PASSWORD);
				admin.setPhone(PHONE);
				administrativeAssistants.add(admin);
				return administrativeAssistants;
			} else {
				return null;
			}
		});


		lenient().when(administrativeAssistantDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
			if (invocation.getArgument(0).equals(ADMINISTRATIVE_ASSISTANT_ID)) {
				administrativeAssistant.setName(ADMINISTRATIVE_ASSISTANT_KEY);
				administrativeAssistant.setEmail(EMAIL);
				administrativeAssistant.setId(ADMINISTRATIVE_ASSISTANT_ID);
				administrativeAssistant.setPassword(PASSWORD);
				administrativeAssistant.setPhone(PHONE);
				return administrativeAssistant;
			} else {
				return null;
			}
		});

		lenient().when(administrativeAssistantDao.findByPhone(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
			if (invocation.getArgument(0).equals(PHONE)) {
				administrativeAssistant.setName(ADMINISTRATIVE_ASSISTANT_KEY);
				administrativeAssistant.setEmail(EMAIL);
				administrativeAssistant.setId(ADMINISTRATIVE_ASSISTANT_ID);
				administrativeAssistant.setPassword(PASSWORD);
				administrativeAssistant.setPhone(PHONE);
				return administrativeAssistant;
			} else {
				return null;
			}
		});
		

		lenient().when(administrativeAssistantDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
			if (invocation.getArgument(0).equals(EMAIL)) {
				administrativeAssistant.setName(ADMINISTRATIVE_ASSISTANT_KEY);
				administrativeAssistant.setEmail(EMAIL);
				administrativeAssistant.setId(ADMINISTRATIVE_ASSISTANT_ID);
				administrativeAssistant.setPassword(PASSWORD);
				administrativeAssistant.setPhone(PHONE);
				return administrativeAssistant;
			} else {
				return null;
			}
		});
		
		lenient().when(administrativeAssistantDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<AdministrativeAssistant> administrativeAssistants = new ArrayList<AdministrativeAssistant>();
			AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
			administrativeAssistant.setName(ADMINISTRATIVE_ASSISTANT_KEY);
			administrativeAssistant.setEmail(EMAIL);
			administrativeAssistant.setId(ADMINISTRATIVE_ASSISTANT_ID);
			administrativeAssistant.setPassword(PASSWORD);
			administrativeAssistant.setPhone(PHONE);
			
			AdministrativeAssistant administrativeAssistant2 = new AdministrativeAssistant();
			administrativeAssistant2.setName(ADMINISTRATIVE_ASSISTANT_KEY2);
			administrativeAssistant2.setEmail(EMAIL2);
			administrativeAssistant2.setId(ADMINISTRATIVE_ASSISTANT_ID2);
			administrativeAssistant2.setPassword(PASSWORD2);
			administrativeAssistant2.setPhone(PHONE2);
			
			administrativeAssistants.add(administrativeAssistant);
			administrativeAssistants.add(administrativeAssistant2);
			return administrativeAssistants;
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(administrativeAssistantDao.save(any(AdministrativeAssistant.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateAdministrativeAssistantNull() {

		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		int administrativeAssistantId = aEmail.hashCode();

		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(administrativeAssistant);
		assertEquals(administrativeAssistantId, administrativeAssistant.getId());
	}

	@Test
	public void testCreateNameNull() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = null;
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant name cannot be empty!", error);
	}

	@Test
	public void testCreateNameSpace() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = " ";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant name cannot be empty!", error);
	}

	@Test
	public void testCreatePasswordNull() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = null;
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant password cannot be empty!", error);
	}
	
	@Test
	public void testCreatePasswordEmpty() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = "";
		int aPhone = 123456789;
		String aEmail = "email@repairsystem.com";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant password cannot be empty!", error);
	}
	
	@Test
	public void testCreateEmailNull() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = null;
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant email cannot be empty!", error);
	}
	
	@Test
	public void testCreateEmailEmpty() {
		AdministrativeAssistant administrativeAssistant = null;
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "";
		String error = null;
		try {
			administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(administrativeAssistant);
		assertEquals("Administrative assistant email cannot be empty!", error);
	}
	
	@Test
	public void testEdit() {
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "hello";
		AdministrativeAssistant administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		String newName = "David";
		String newPassword = "poopy";
		int newPhone = 4321;
		String newEmail = "goodbye";
		String error = null;
		int id = aEmail.hashCode();
		try {
			administrativeAssistant = administrativeAssistantService.editAdmin(aEmail, newName, newPassword, newPhone);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(administrativeAssistant);
		assertEquals(administrativeAssistant.getPhone(), newPhone);
		assertEquals(administrativeAssistant.getName(), newName);
		assertEquals(administrativeAssistant.getPassword(), newPassword);
		assertEquals(administrativeAssistant.getId(), id);
	}
	
	@Test
	public void testGetAdminById() {
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "hello";
		AdministrativeAssistant administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		int id = aEmail.hashCode();
		String error = null;
		
		ADMINISTRATIVE_ASSISTANT_KEY = name;
		ADMINISTRATIVE_ASSISTANT_ID = id;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		try {
			administrativeAssistant = administrativeAssistantService.getAdminById(id);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(administrativeAssistant);
		assertEquals(administrativeAssistant.getEmail(), aEmail);
		assertEquals(administrativeAssistant.getPhone(), aPhone);
		assertEquals(administrativeAssistant.getName(), name);
		assertEquals(administrativeAssistant.getPassword(), aPassword);
		assertEquals(administrativeAssistant.getId(), id);
	}
	
	@Test
	public void testGetAdminByEmail() {
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "hello";
		AdministrativeAssistant administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		int id = aEmail.hashCode();
		String error = null;
		
		ADMINISTRATIVE_ASSISTANT_KEY = name;
		ADMINISTRATIVE_ASSISTANT_ID = id;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		try {
			administrativeAssistant = administrativeAssistantService.getAdminByEmail(aEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(administrativeAssistant);
		assertEquals(administrativeAssistant.getEmail(), aEmail);
		assertEquals(administrativeAssistant.getPhone(), aPhone);
		assertEquals(administrativeAssistant.getName(), name);
		assertEquals(administrativeAssistant.getPassword(), aPassword);
		assertEquals(administrativeAssistant.getId(), id);
	}
	
	@Test
	public void testGetAdminByName() {
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "hello";
		administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		List<AdministrativeAssistant> administrativeAssistants = new ArrayList<AdministrativeAssistant>();
		int id = aEmail.hashCode();
		String error = null;
		
		ADMINISTRATIVE_ASSISTANT_KEY = name;
		ADMINISTRATIVE_ASSISTANT_ID = id;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		try {
			administrativeAssistants = administrativeAssistantService.getAdminsByName(name);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(administrativeAssistants.get(0));
		assertEquals(administrativeAssistants.get(0).getEmail(), aEmail);
		assertEquals(administrativeAssistants.get(0).getPhone(), aPhone);
		assertEquals(administrativeAssistants.get(0).getName(), name);
		assertEquals(administrativeAssistants.get(0).getPassword(), aPassword);
		assertEquals(administrativeAssistants.get(0).getId(), id);
	}
	
	@Test
	public void testGetAdminByPhone() {
		String name = "Oscar";
		String aPassword = "123412";
		int aPhone = 123456789;
		String aEmail = "hello";
		AdministrativeAssistant administrativeAssistant = administrativeAssistantService.createAdmin(name, aPassword, aPhone, aEmail);
		int id = aEmail.hashCode();
		String error = null;
		
		ADMINISTRATIVE_ASSISTANT_KEY = name;
		ADMINISTRATIVE_ASSISTANT_ID = id;
		PASSWORD = aPassword;
		PHONE = aPhone;
		EMAIL = aEmail;
		try {
			administrativeAssistant = administrativeAssistantService.getAdminByNumber(aPhone);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(administrativeAssistant);
		assertEquals(administrativeAssistant.getEmail(), aEmail);
		assertEquals(administrativeAssistant.getPhone(), aPhone);
		assertEquals(administrativeAssistant.getName(), name);
		assertEquals(administrativeAssistant.getPassword(), aPassword);
		assertEquals(administrativeAssistant.getId(), id);
	}
	
	@Test
	public void testGetAllAdmins() {
		String name1 = "Oscar";
		String password1 = "123412";
		int phone1 = 123456789;
		String email1 = "hello";
		administrativeAssistantService.createAdmin(name1, password1, phone1, email1);
		int id1 = email1.hashCode();
		
		String name2 = "Oscar";
		String password2 = "123412";
		int phone2 = 123456789;
		String email2 = "hello";
		administrativeAssistantService.createAdmin(name2, password2, phone2, email2);
		int id2 = email1.hashCode();
		
		String error = null;
		
		List<AdministrativeAssistant> administrativeAssistants = new ArrayList<AdministrativeAssistant>();
		
		ADMINISTRATIVE_ASSISTANT_KEY = name1;
		ADMINISTRATIVE_ASSISTANT_ID = id1;
		PASSWORD = password1;
		PHONE = phone1;
		EMAIL = email1;
		
		ADMINISTRATIVE_ASSISTANT_KEY2 = name2;
		ADMINISTRATIVE_ASSISTANT_ID2 = id2;
		PASSWORD2 = password2;
		PHONE2 = phone2;
		EMAIL2 = email2;
		try {
			administrativeAssistants = administrativeAssistantService.getAllAdmins();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(administrativeAssistants.get(0));
		assertEquals(administrativeAssistants.get(0).getEmail(), email1);
		assertEquals(administrativeAssistants.get(0).getPhone(), phone1);
		assertEquals(administrativeAssistants.get(0).getName(), name1);
		assertEquals(administrativeAssistants.get(0).getPassword(), password1);
		assertEquals(administrativeAssistants.get(0).getId(), id1);
		assertNotNull(administrativeAssistants.get(1));
		assertEquals(administrativeAssistants.get(1).getEmail(), email2);
		assertEquals(administrativeAssistants.get(1).getPhone(), phone2);
		assertEquals(administrativeAssistants.get(1).getName(), name2);
		assertEquals(administrativeAssistants.get(1).getPassword(), password2);
		assertEquals(administrativeAssistants.get(1).getId(), id2);
	}
	
	

}
