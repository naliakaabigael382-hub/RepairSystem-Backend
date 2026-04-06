package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;

@Entity
public class Car
{

  public Car(int id, CarType type, boolean winterTires, int numOfKm, Customer customer) {
	 carId = id;
	 this.carType = type;
	 this.winterTires = winterTires;
	 numOfKilometers = numOfKm;
	 this.appointments = new ArrayList<Appointment>();
	 this.customer = customer;
  }

  public Car() {

  }

  public enum CarType { SEDAN, COUPE, SPORTS, HATCHBACK, MINIVAN, STATIONWAGON, CONVERTIBLE, TRUCK, SUV, OTHER}

  private int carId;

  @Id
  public int getId() {
	  return this.carId;
  }

  public void setId(int aId) {
	  this.carId = aId;
  }

  private CarType carType;

  public CarType getCarType(){
    return carType;
  }

  public void setCarType(CarType aType)
  {
    carType = aType;
  }

  private boolean winterTires;

  public boolean getWinterTires(){
    return winterTires;
  }

  public void setWinterTires(boolean aWinterTires)
  {
    winterTires = aWinterTires;
  }

  private int numOfKilometers;

  public int getNumOfKilometers(){
    return numOfKilometers;
  }

  public void setNumOfKilometers(int aNumOfKilometers)
  {
    numOfKilometers = aNumOfKilometers;
  }

  private Customer customer;

  @ManyToOne()
  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer c){
    customer = c;
  }

  private List<Appointment> appointments;

  @OneToMany(cascade={CascadeType.ALL})
  public List<Appointment> getAppointments()
  {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointmentList){
    appointments = appointmentList;
  }

  public void addAppointment(Appointment appointment) {
	  appointments.add(appointment);
  }

  public void removeAppointment(Appointment appointment) {
	  appointments.remove(appointment);
  }

}
