package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment
{

    public Appointment(Customer customer, int id, TimeSlot time, Car car, String note) {
        this.customer = customer;
        this.Id = id;
        this.status = AppointmentStatus.AppointmentBooked;
        this.note = note;
        this.timeSlot = time;
        this.mechanics = new ArrayList<Mechanic>();
        this.customer = customer;
        this.images = new ArrayList<Image>();
        this.services = new ArrayList<Service>();
        this.car = car;
    }

    public Appointment() {
        this.images = new ArrayList<Image>();
        this.services = new ArrayList<Service>();
        this.mechanics = new ArrayList<Mechanic>();
        this.customer = null;
        this.car = null;
        this.timeSlot = null;
    }

    public void addMechanic(Mechanic mechanic)
    {
        this.mechanics.add(mechanic);
    }

    public void removeMechanic(Mechanic mechanic)
    {
        this.mechanics.remove(mechanic);
    }

    public void addImage(Image image)
    {
        this.images.add(image);
    }

    public void addService(Service service)
    {
        this.services.add(service);
    }

    public void removeService(Service service) {
        this.services.remove(service);
    }

    public void updateAppointmentStatus(AppointmentStatus status)
    {
        this.status = status;
    }
    public enum AppointmentStatus { AppointmentBooked, CarReceived, InRepair, Completed };

    private int Id;

    @Id
    public int getId() {
        return this.Id;
    }

    public void setId(int aId) {
        this.Id = aId;
    }

    private String note;

    public String getNote()
    {
        return note;
    }

    public void setNote(String aNote)
    {
        note = aNote;
    }

    private AppointmentStatus status;

    public AppointmentStatus getStatus()
    {
        return status;
    }

    public void setStatus(AppointmentStatus aStatus)
    {
        status = aStatus;
    }

    private TimeSlot timeSlot;

    @ManyToOne
    public TimeSlot getTimeSlot()
    {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot appTime){
        timeSlot = appTime;
    }

    private List<Mechanic> mechanics;

    @ManyToMany
    public List<Mechanic> getMechanics()
    {
        return mechanics;
    }

    public void setMechanics(List<Mechanic> mechanicList) {
        mechanics = mechanicList;
    }

    private Customer customer;

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer c) {
        customer = c;
    }

    private List<Service> services;

    @ManyToMany
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> serviceList){
        services = serviceList;
    }

    private List<Image> images;

    @OneToMany(cascade={CascadeType.ALL})
    public List<Image> getImages()
    {
        return images;
    }

    public void setImages(List<Image> imageList){
        images = imageList;
    }


    private Car car;

    @ManyToOne()
    public Car getCar()
    {
        return car;
    }

    public void setCar(Car c) {
        car = c;
    }

}