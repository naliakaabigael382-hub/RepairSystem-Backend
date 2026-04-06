package ca.mcgill.ecse321.repairsystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

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
public class TestTimeSlotService {
	@Mock
	private TimeSlotRepository timeslotDao;

	@InjectMocks
	private TimeSlotService timeSlotService;

	private static int TIME_SLOT_ID = 43123;
	private static LocalDateTime  START_TIME = LocalDateTime.of(2021, 3, 13, 10, 00);
	private static LocalDateTime END_TIME = LocalDateTime.of(2021, 3,13,12,00);
	private static List<Mechanic> MECHANICS = new ArrayList<Mechanic>();
	private static List<Appointment> APPOINTMENTS = new ArrayList<Appointment>();

	private static int TIME_SLOT_ID2 = 12345;
	private static LocalDateTime  START_TIME2 = LocalDateTime.of(2021, 4, 13, 10, 00);
	private static LocalDateTime END_TIME2 = LocalDateTime.of(2021,4,13,12,00);
	private static List<Mechanic> MECHANICS2 = new ArrayList<Mechanic>();
	private static List<Appointment> APPOINTMENTS2 = new ArrayList<Appointment>();

	@BeforeEach
	public void setMockOutput()
	{
		lenient().when(timeslotDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TIME_SLOT_ID)) {
				TimeSlot time = new TimeSlot();
				time.setId(TIME_SLOT_ID);
				time.setStartTime(START_TIME);
				time.setEndTime(END_TIME);
				time.setAppointments(APPOINTMENTS);
				time.setMechanics(MECHANICS);
				return time;
			} else {
				return null;
			}
		});

		lenient().when(timeslotDao.findByStartTime(any(LocalDateTime.class))).thenAnswer((InvocationOnMock invocation) -> {
			List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
			TimeSlot time = new TimeSlot();
			LocalDateTime startTime = invocation.getArgument(0);
			if (startTime == START_TIME) {
				time.setId(TIME_SLOT_ID);
				time.setStartTime(START_TIME);
				time.setEndTime(END_TIME);
				time.setAppointments(APPOINTMENTS);
				time.setMechanics(MECHANICS);
				timeSlots.add(time);
				return timeSlots;
			} else {
				return null;
			}
		});

		lenient().when(timeslotDao.findByEndTime(any(LocalDateTime.class))).thenAnswer((InvocationOnMock invocation) -> {
			List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
			TimeSlot time = new TimeSlot();
			LocalDateTime endTime = invocation.getArgument(0);
			if (endTime == END_TIME) {
				time.setId(TIME_SLOT_ID);
				time.setStartTime(START_TIME);
				time.setEndTime(END_TIME);
				time.setAppointments(APPOINTMENTS);
				time.setMechanics(MECHANICS);
				timeSlots.add(time);
				return timeSlots;
			} else {
				return null;
			}
		});

		lenient().when(timeslotDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
			TimeSlot time = new TimeSlot();
			TimeSlot time2 = new TimeSlot();

			time.setId(TIME_SLOT_ID);
			time.setStartTime(START_TIME);
			time.setEndTime(END_TIME);
			time.setAppointments(APPOINTMENTS);
			time.setMechanics(MECHANICS);
			timeSlots.add(time);

			time2.setId(TIME_SLOT_ID2);
			time2.setStartTime(START_TIME2);
			time2.setEndTime(END_TIME2);
			time2.setAppointments(APPOINTMENTS2);
			time2.setMechanics(MECHANICS2);
			timeSlots.add(time2);

			return timeSlots;
		});


		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(timeslotDao.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);

	}


	@Test
	/**
	 * Verifies the creation of a time slot object
	 */
	public void testCreateTimeSlot()
	{

		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.of(2021, 3,14,12,00);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int timeId =startTime.hashCode() * endTime.hashCode();
		TimeSlot time = null;
		try {
			time = timeSlotService.createTimeSlot(startTime, endTime);
		}catch(IllegalArgumentException e)
		{
			fail();
		}

		assertNotNull(time);
		assertEquals(timeId, time.getId());
	}

	/**
	 * Verifies that the start time of a service object is not null
	 */
	@Test
	public void testCreateTimeSlotStartTimeNull()
	{

		LocalDateTime startTime = null;
		LocalDateTime endTime = LocalDateTime.of(2021, 3,14,12,00);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		TimeSlot time = null;
		String error = null;

		try {
			time = timeSlotService.createTimeSlot(startTime, endTime);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}

		assertNull(time);
		assertEquals("Start time cannot be null", error);
	}


	/**
	 * Verifies that the end time of a service object is not null
	 */
	@Test
	public void testCreateTimeSlotEndTimeNull()
	{

		LocalDateTime startTime = LocalDateTime.of(2021, 3, 13, 10, 00);
		LocalDateTime endTime = null;
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		TimeSlot time = null;
		String error = null;

		try {
			time = timeSlotService.createTimeSlot(startTime, endTime);
		}catch(IllegalArgumentException e){
			error = e.getMessage();
		}

		assertNull(time);
		assertEquals("End time cannot be null", error);
	}


	@Test
	public void testGetTimeSlotbyId() {
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.of(2021, 3,14,12,00);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int timeId =startTime.hashCode() * endTime.hashCode();
		TimeSlot time = timeSlotService.createTimeSlot(startTime, endTime);
		String error = null;

		TIME_SLOT_ID = timeId;
		START_TIME = startTime;
		END_TIME = endTime;
		MECHANICS = mechanics;
		APPOINTMENTS = appointments;

		try {
			time = timeSlotService.getTimeSlotById(timeId);
		}catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}

		assertNotNull(time);
		assertEquals(time.getAppointments(), appointments);
		assertEquals(time.getStartTime(), startTime);
		assertEquals(time.getEndTime(), endTime);
		assertEquals(time.getMechanics(), mechanics);
		assertEquals(time.getId(), timeId);


	}


	@Test
	public void testGetTimeSlotbyStartTime() {
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.of(2021, 3,14,12,00);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int timeId =startTime.hashCode() * endTime.hashCode();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		String error = null;
		TimeSlot time = timeSlotService.createTimeSlot(startTime, endTime);


		TIME_SLOT_ID = timeId;
		START_TIME = startTime;
		END_TIME = endTime;
		MECHANICS = mechanics;
		APPOINTMENTS = appointments;

		try {
			timeSlots = timeSlotService.getTimeSlotsByStartTime(startTime);
		}
		catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}

		assertNotNull(timeSlots.get(0));
		assertEquals(timeSlots.get(0).getEndTime(), endTime);
		assertEquals(timeSlots.get(0).getId(), timeId);
		assertEquals(timeSlots.get(0).getMechanics(), mechanics);
		assertEquals(timeSlots.get(0).getStartTime(), startTime);
	}

	@Test
	public void testGetTimeSlotbyEndTime() {
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.of(2021, 3,14,12,00);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int timeId =startTime.hashCode() * endTime.hashCode();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		String error = null;
		TimeSlot time = timeSlotService.createTimeSlot(startTime, endTime);

		TIME_SLOT_ID = timeId;
		START_TIME = startTime;
		END_TIME = endTime;
		MECHANICS = mechanics;
		APPOINTMENTS = appointments;

		try {
			timeSlots = timeSlotService.getTimeSlotsByEndTime(endTime);
		}
		catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}

		assertNotNull(timeSlots.get(0));
		assertEquals(timeSlots.get(0).getEndTime(), endTime);
		assertEquals(timeSlots.get(0).getId(), timeId);
		assertEquals(timeSlots.get(0).getMechanics(), mechanics);
		assertEquals(timeSlots.get(0).getStartTime(), startTime);
	}

	@Test
	public void testGetAllTimeSlots() {
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.of(2021, 3,14,12,00);
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		List<Appointment> appointments = new ArrayList<Appointment>();
		int timeId =startTime.hashCode() * endTime.hashCode();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		String error = null;
		TimeSlot time = timeSlotService.createTimeSlot(startTime, endTime);

		TIME_SLOT_ID = timeId;
		START_TIME = startTime;
		END_TIME = endTime;
		MECHANICS = mechanics;
		APPOINTMENTS = appointments;



		LocalDateTime startTime2 = LocalDateTime.of(2021, 3,1,12,00);
		LocalDateTime endTime2 = LocalDateTime.of(2021, 3,2,12,00);
		List<Mechanic> mechanics2 = new ArrayList<Mechanic>();
		List<Appointment> appointments2 = new ArrayList<Appointment>();
		int timeId2 =startTime.hashCode() * endTime.hashCode();
		List<TimeSlot> timeSlots2 = new ArrayList<TimeSlot>();
		TimeSlot time2 = timeSlotService.createTimeSlot(startTime, endTime);

		TIME_SLOT_ID2 = timeId2;
		START_TIME2 = startTime2;
		END_TIME2 = endTime2;
		MECHANICS2 = mechanics2;
		APPOINTMENTS2 = appointments2;

		try {
			timeSlots = timeSlotService.getAllTimeSlots();
		}
		catch(IllegalArgumentException e)
		{
			error = e.getMessage();
		}

		assertNotNull(timeSlots.get(0));
		assertEquals(timeSlots.get(0).getEndTime(), endTime);
		assertEquals(timeSlots.get(0).getId(), timeId);
		assertEquals(timeSlots.get(0).getMechanics(), mechanics);
		assertEquals(timeSlots.get(0).getStartTime(), startTime);

		assertNotNull(timeSlots.get(1));
		assertEquals(timeSlots.get(1).getEndTime(), endTime2);
		assertEquals(timeSlots.get(1).getId(), timeId2);
		assertEquals(timeSlots.get(1).getMechanics(), mechanics2);
		assertEquals(timeSlots.get(1).getStartTime(), startTime2);

	}

}
