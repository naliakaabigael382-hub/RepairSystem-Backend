package ca.mcgill.ecse321.repairsystem.dto;

import java.util.*;
import java.time.LocalDateTime;

public class TimeSlotDto{
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public TimeSlotDto(LocalDateTime aStartTime, LocalDateTime aEndTime, int Id, List<MechanicDto> mechanics, List<AppointmentDto> appointments){
		this.startTime = aStartTime;
		this.endTime = aEndTime;
		this.id = Id;
		this.mechanics = mechanics;
		this.appointments = appointments;
	}
	
	private String startDay;
	private String startTimeFormat;
	private String endTimeFormat;
	
	public TimeSlotDto(int id, LocalDateTime aStartTime, LocalDateTime aEndTime){
		this.id = id;
		this.startTime = aStartTime;
		this.endTime = aEndTime;
		this.startDay = aStartTime.getDayOfWeek().toString();
		String start = "";
		String end = "";
		if(aStartTime.getHour() < 10) {
			start = "0" + aStartTime.getHour();
		} else {
			start = "" + aStartTime.getHour();
		}
		if(aEndTime.getHour() < 10) {
			end = "0" + aEndTime.getHour();
		} else {
			end = "" + aEndTime.getHour();
		}
		if(aStartTime.getMinute() < 10) {
			start = start.concat(":0" + aStartTime.getMinute());
		} else {
			start = start.concat(":" + aStartTime.getMinute());
		}
		if(aEndTime.getMinute() < 10) {
			end = end.concat(":0" + aEndTime.getMinute());
		} else {
			end = end.concat(":" + aEndTime.getMinute());
		}
		this.startTimeFormat = start;
		this.endTimeFormat = end;
	}
	
	public TimeSlotDto(int id){
		this.id = id;
	}

	public TimeSlotDto() {
		
	}

	public void setStartTime(LocalDateTime aStartTime){
		this.startTime = aStartTime;
	}

	public void setEndTime(LocalDateTime aEndTime)  {
		this.endTime = aEndTime;
	}
	
	public void setStartDay(String startDay){
		this.startDay = startDay;
	}

	public String getStartDay()  {
		return this.startDay;
	}
	
	public void setStartTimeFormat(String num){
		this.startTimeFormat = num;
	}

	public String getStartTimeFormat()  {
		return this.startTimeFormat;
	}
	
	public void setEndTimeFormat(String num){
		this.endTimeFormat = num;
	}

	public String getEndTimeFormat()  {
		return this.endTimeFormat;
	}

	public LocalDateTime getStartTime(){
		return this.startTime;
	}

	public LocalDateTime getEndTime(){
		return this.endTime;
	}

	private List<MechanicDto> mechanics;
	
	public List<MechanicDto> getMechanic(){
		return this.mechanics;
	}
	public void setMechanic(List<MechanicDto> mechanic){
		this.mechanics=mechanic;
	}
	
	private List<AppointmentDto> appointments;
	
	public List<AppointmentDto> getAppointment(){
		return this.appointments;
	}
	public void setAppointment(List<AppointmentDto> appointment){
		this.appointments=appointment;
	}
	
}


