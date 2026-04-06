 package ca.mcgill.ecse321.repairsystem.model;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import java.util.*;

@Entity
public class Service{

	public enum ServiceType { CarRepair, OilChange, RegularCheckup, CarWash, TireChange, RoadsideAssistance, Towing, CarInspection, OTHER }

	public Service(ServiceType aType, int aPrice){
		this.serviceType = aType;
		this.price = aPrice;
		this.mechanics = new ArrayList<Mechanic>();
		this.appointments = new ArrayList<Appointment>();
	}
	
	public Service() {
		
	}
	
	private ServiceType serviceType;
	public void setServiceType(ServiceType aType){
		serviceType = aType;
	}
	@Id
	public ServiceType getServiceType() {
		return serviceType;  
	}

	private int price;

	public void setPrice(int aPrice){
		price = aPrice;
	}


	public int getPrice(){
		return price;
	}

	private List<Appointment> appointments;
	@ManyToMany
	public List<Appointment> getAppointments(){
		return this.appointments;
	}
	public void setAppointments(List<Appointment> appointments){
		this.appointments=appointments;
	}

	private List<Mechanic> mechanics;
	@ManyToMany
	public List<Mechanic> getMechanics(){
		return this.mechanics;
	}
	public void setMechanics(List<Mechanic> mechanics){
		this.mechanics = mechanics;
	}
	
	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
	}
	
	public void addMechanic(Mechanic mechanic) {
		mechanics.add(mechanic);
	}
	
	public void removeAppointment(Appointment appointment) {
		appointments.remove(appointment);
	}
	
	public void removeMechanic(Mechanic mechanic) {
		mechanics.remove(mechanic);
	}
	

}