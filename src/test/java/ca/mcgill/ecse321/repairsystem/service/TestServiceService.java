package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
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
import ca.mcgill.ecse321.repairsystem.model.Service.ServiceType;

@ExtendWith(MockitoExtension.class)
public class TestServiceService {
	@Mock
	private ServiceRepository serviceDao;

	@InjectMocks
	private ServiceService serviceService;

	private static ServiceType SERVICE_TYPE= ServiceType.CarRepair;
	private static int PRICE = 340;
	private static List<Mechanic> MECHANICS = new ArrayList<Mechanic>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();


	private static ServiceType SERVICE_TYPE2= ServiceType.CarRepair;
	private static int PRICE2 = 342;
	private static List<Mechanic> MECHANICS2 = new ArrayList<Mechanic>();
	private static List<Appointment> APPOINTMENTS2 = new ArrayList<Appointment>();
	@BeforeEach
	public void setMockOutput() {

		lenient().when(serviceDao.findByServiceType(any(Service.ServiceType.class))).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SERVICE_TYPE)) {
				Service service = new Service();
				service.setServiceType(SERVICE_TYPE);
				service.setPrice(PRICE);
				service.setAppointments(APPOINTMENTS);
				service.setMechanics(MECHANICS);
				return service;
			} else {
				return null;
			}
		});


		lenient().when(serviceDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Service> services = new ArrayList<Service>();
			Service service1 = new Service();
			Service service2 = new Service();

			service1.setServiceType(SERVICE_TYPE);
			service1.setPrice(PRICE);
			service1.setAppointments(APPOINTMENTS);
			service1.setMechanics(MECHANICS);
			services.add(service1);

			service2.setServiceType(SERVICE_TYPE2);
			service2.setPrice(PRICE2);
			service2.setAppointments(APPOINTMENTS2);
			service2.setMechanics(MECHANICS2);
			services.add(service2);

			return services;
		});


		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(serviceDao.save(any(Service.class))).thenAnswer(returnParameterAsAnswer);	
	}

	@Test
	/**
	 * Verifies the creation of a service object
	 */
	public void testCreateService()
	{

		ServiceType type = ServiceType.RegularCheckup;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int price = 142;
		Service service = null;
		try {
			service = serviceService.createService(type, price);
		}catch(IllegalArgumentException e)
		{
			fail();
		}

		assertNotNull(service);
		assertEquals(type, service.getServiceType());
		assertEquals(mechanics, service.getMechanics());
		assertEquals(appointments, service.getAppointments());
	}

	@Test
	/**
	 * Verifies that the service type of a service object is not null
	 */
	public void testCreateServiceTypeNull()
	{

		ServiceType type = null;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int price = 142;
		Service service = null;
		String error = null;

		try {
			service = serviceService.createService(type, price);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}

		assertNull(service);
		assertEquals("Service type cannot be null", error);
	}

	@Test
	public void testGetAllServiceType() {

		ServiceType type = ServiceType.RegularCheckup;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int price = 142;
		Service service = serviceService.createService(type, price);

		ServiceType type2 = ServiceType.RegularCheckup;
		List<Mechanic> mechanics2 = new ArrayList<Mechanic>();
		List<Appointment> appointments2 = new ArrayList<Appointment>();
		int price2 = 143;
		Service service2 = serviceService.createService(type, price);

		String error = null;
		List<Service> services = new ArrayList<Service>();

		SERVICE_TYPE= type;
		PRICE = price;
		MECHANICS = mechanics;
		APPOINTMENTS = appointments;
		SERVICE_TYPE2= type2;
		PRICE2 = price2;
		MECHANICS2 = mechanics2;
		APPOINTMENTS2 = appointments2;
		try {
			services = serviceService.getAllServices();
		}
		catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}

		assertNotNull(services.get(0));
		assertEquals(services.get(0).getAppointments(),appointments);
		assertEquals(services.get(0).getMechanics(), mechanics);
		assertEquals(services.get(0).getPrice(), price);
		assertEquals(services.get(0).getServiceType(), type);

		assertNotNull(services.get(1));
		assertEquals(services.get(1).getAppointments(),appointments2);
		assertEquals(services.get(1).getMechanics(), mechanics2);
		assertEquals(services.get(1).getPrice(), price2);
		assertEquals(services.get(1).getServiceType(), type2);


	}

	@Test
	public void testGetServiceByServiceType() {
		ServiceType type = ServiceType.RegularCheckup;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int price = 142;
		Service service = serviceService.createService(type, price);
		String error = null;

		SERVICE_TYPE= type;
		PRICE = price;
		MECHANICS = mechanics;
		APPOINTMENTS = appointments;

		try {
			service = serviceService.getServiceByServiceType(type);
		}
		catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}

		assertNotNull(service);
		assertEquals(service.getAppointments(), appointments);
		assertEquals(service.getMechanics(), mechanics);
		assertEquals(service.getPrice(), price);
		assertEquals(service.getServiceType(), type);


	}

}
