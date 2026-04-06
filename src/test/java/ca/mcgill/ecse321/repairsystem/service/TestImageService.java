package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
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
public class TestImageService {
	@Mock
	private ImageRepository imageDao;
	@Mock
	private AppointmentRepository appointmentDao;

	@InjectMocks
	private ImageService imageService;

	private static String IMAGE_URL = "TestImage";
	private static int IMAGE_ID = 123;
	private static Appointment APPOINTMENT = new Appointment();

	private static String IMAGE_URL2 = "TestImage2";
	private static int IMAGE_ID2 = 124;
	private static Appointment APPOINTMENT2 = new Appointment();
	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(imageDao.findByAppointment(any(Appointment.class))).thenAnswer( (InvocationOnMock invocation) -> {
			List<Image> images = new ArrayList<Image>();
			Image image = new Image();
			if(invocation.getArgument(0).equals(APPOINTMENT)) {
				image.setAppointment(APPOINTMENT);
				image.setId(IMAGE_ID);
				image.setUrl(IMAGE_URL);
				images.add(image);
				return images;
			} else {
				return null;
			}
		});

		lenient().when(imageDao.findByUrl(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			Image image = new Image();
			if(invocation.getArgument(0).equals(IMAGE_URL)) {
				image.setAppointment(APPOINTMENT);
				image.setId(IMAGE_ID);
				image.setUrl(IMAGE_URL);
				return image;
			} else {
				return null;
			}
		});

		lenient().when(imageDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Image> images = new ArrayList<Image>();
			Image image = new Image();
			image.setAppointment(APPOINTMENT);
			image.setId(IMAGE_ID);
			image.setUrl(IMAGE_URL);
			images.add(image);

			Image image2 = new Image();
			image2.setAppointment(APPOINTMENT2);
			image2.setId(IMAGE_ID2);
			image2.setUrl(IMAGE_URL2);
			images.add(image2);
			images.add(image);
			return images;
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(imageDao.save(any(Image.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(appointmentDao.save(any(Appointment.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateImageNull() {
		Image image = null;
		String url = "url";
		Appointment appointment = new Appointment();
		int imageId = url.hashCode();
		try {
			image = imageService.createImage(url, appointment);	
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(image);
		assertEquals(imageId, image.getId());
	}

	@Test
	public void testCreateUrlNull() {
		Image image = null;
		String url = null;
		Appointment appointment = new Appointment();
		String error = null;
		try {
			image = imageService.createImage(url, appointment);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(image);
		assertEquals("Image url cannot be empty!", error);
	}

	@Test
	public void testCreateUrlEmpty() {
		Image image = null;
		String url = "";
		Appointment appointment = new Appointment();
		String error = null;
		try {
			image = imageService.createImage(url, appointment);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(image);
		assertEquals("Image url cannot be empty!", error);
	}

	@Test
	public void testCreateAppointmentNull() {
		Image image = null;
		String url = "url";
		Appointment appointment = null;
		String error = null;
		try {
			image = imageService.createImage(url, appointment);	
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(image);
		assertEquals("Image appointment cannot be empty!", error);
	}


	@Test
	public void testGetAllImages() {

		String url = "url";
		Appointment appointment = new Appointment();
		int imageId = url.hashCode();
		Image image = imageService.createImage(url, appointment);
		List<Image> images = new ArrayList<Image>();
		IMAGE_URL = url;
		IMAGE_ID = imageId;
		APPOINTMENT = appointment;
		String error = null;


		String url2 = "url";
		Appointment appointment2 = new Appointment();
		int imageId2 = url.hashCode();
		Image image2 = imageService.createImage(url, appointment);
		IMAGE_URL2 = url2;
		IMAGE_ID2 = imageId2;
		APPOINTMENT2 = appointment2;

		try {
			images = imageService.getAllImages();
		}
		catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}	
		assertNotNull(images.get(0));
		assertEquals(images.get(0).getAppointment(), appointment);
		assertEquals(images.get(0).getId(), imageId);
		assertEquals(images.get(0).getUrl(), url);
		assertNotNull(images.get(1));
		//assertEquals(images.get(1).getAppointment(), appointment2);
		assertEquals(images.get(1).getId(), imageId2);
		assertEquals(images.get(1).getUrl(), url2);
	}


	@Test
	public void testGetImageByUrl() {
		String url = "url";
		Appointment appointment = new Appointment();
		int imageId = url.hashCode();
		Image image = imageService.createImage(url, appointment);
		List<Image> images = new ArrayList<Image>();
		IMAGE_URL = url;
		IMAGE_ID = imageId;
		APPOINTMENT = appointment;
		String error = null;

		try {
			image = imageService.getImageByUrl(url);
		}
		catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}	
		assertNotNull(image);
		assertEquals(image.getAppointment(), appointment);
		assertEquals(image.getId(), imageId);
		assertEquals(image.getUrl(), url);
	}

	@Test
	public void testGetImageByAppointment() {
		String url = "url";
		Appointment appointment = new Appointment();
		int imageId = url.hashCode();
		Image image = imageService.createImage(url, appointment);
		List<Image> images = new ArrayList<Image>();
		IMAGE_URL = url;
		IMAGE_ID = imageId;
		APPOINTMENT = appointment;
		String error = null;

		try {
			images = imageService.getImagesByAppointment(appointment);
		}
		catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}	
		assertNotNull(images.get(0));
		assertEquals(images.get(0).getAppointment(), appointment);
		assertEquals(images.get(0).getId(), imageId);
		assertEquals(images.get(0).getUrl(), url);
	}


}
