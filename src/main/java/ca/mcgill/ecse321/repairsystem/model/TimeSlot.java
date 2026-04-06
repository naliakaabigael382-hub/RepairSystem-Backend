package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;



@Entity
public class TimeSlot{
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}
	@Id
	public int getId() {
		return this.id;
	}
	

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public TimeSlot(LocalDateTime aStartTime, LocalDateTime aEndTime, int Id){
		startTime = aStartTime;
		endTime = aEndTime;
		this.id = Id;
		this.mechanics = new ArrayList<Mechanic>();
		this.appointments = new ArrayList<Appointment>();
	}

	public TimeSlot() {
		
	}

	public void setStartTime(LocalDateTime aStartTime){
		this.startTime = aStartTime;
	}

	public void setEndTime(LocalDateTime aEndTime)  {
		this.endTime = aEndTime;
	}

	public LocalDateTime getStartTime(){
		return this.startTime;
	}

	public LocalDateTime getEndTime(){
		return this.endTime;
	}

	private List<Mechanic> mechanics;
	@ManyToMany
	public List<Mechanic> getMechanics(){
		return this.mechanics;
	}
	public void setMechanics(List<Mechanic> mechanic){
		this.mechanics=mechanic;
	}
	
	private List<Appointment> appointments;
	
	@OneToMany(cascade = {CascadeType.ALL})
	public List<Appointment> getAppointments(){
		return this.appointments;
	}
	public void setAppointments(List<Appointment> appointment){
		this.appointments=appointment;
	}
	
	
	private RepairSystem repairSystem;
	@ManyToOne
	public RepairSystem getRepairSystem() {
		return this.repairSystem;
	}
	public void setRepairSystem(RepairSystem repairSystem) {
		this.repairSystem = repairSystem;
	}
	
	public void addMechanic(Mechanic mechanic) {
		mechanics.add(mechanic);
	}
	
	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
	}
	
	public void removeMechanic(Mechanic mechanic) {
		mechanics.remove(mechanic);
	}
	
	public void removeAppointment(Appointment appointment) {
		appointments.remove(appointment);
	}
	
	
}


