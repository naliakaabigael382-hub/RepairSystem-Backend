package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;


@Entity
public class Mechanic extends Person{


	public Mechanic(String aName, int id, String aPassword, long aPhone, String aEmail, List<Service> allCapabilities)
	{
	  super(aName, id, aPassword, aPhone, aEmail);    
	  services = allCapabilities;
	  appointments = new ArrayList<Appointment>();
	  timeSlots = new ArrayList<TimeSlot>();
	}
	
	public Mechanic() {
	}

  //Mechanic Associations
  private List<TimeSlot> timeSlots;
  
  @ManyToMany
  public List<TimeSlot> getTimeSlots()
  {
    return this.timeSlots;
  }

 public void setTimeSlots(List<TimeSlot> workHours) {
	 this.timeSlots = workHours;
 }
  
  private List<Service> services;
  
  @ManyToMany
  public List<Service> getServices()
  {
	  return this.services;
  }

  public void setServices(List<Service> service) {
	  this.services = service;
  }
  
  private List<Appointment> appointments;
  
  @ManyToMany
  public List<Appointment> getAppointments()
  {
    return this.appointments;
  }
  
  public void setAppointments(List<Appointment> appointment) {
	  this.appointments=appointment;
  }
  
  public void addService(Service service) {
	  services.add(service);
  }
  
  public void removeService(Service service) {
	  services.remove(service);
  }
  
  public void addAppointment(Appointment appointment) {
	  appointments.add(appointment);
  }
  
  public void removeAppointment(Appointment appointment) {
	  appointments.remove(appointment);
  }
  
  public void addTimeSlot(TimeSlot t) {
	  timeSlots.add(t);
  }
  
  public void removeTimeSlot(TimeSlot t) {
	  timeSlots.remove(t);
  }

}