package ca.mcgill.ecse321.repairsystem.model;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Image
{

  public Image(int id, String url, Appointment a) {
	  this.imageId = id;
	  this.url = url;
	  this.appointment = a;
  }
  
  public Image() {
  }
  
  //Image Associations
  private Appointment appointment;
 
  @ManyToOne
  public Appointment getAppointment()
  {
    return this.appointment;
  }
  
  public void setAppointment(Appointment aAppointment)
  {
    this.appointment = aAppointment;
  }
  
  private int imageId;
  
  @Id
  public int getId() {
	  return this.imageId;
  }
  
  public void setId(int aId) {
	  this.imageId = aId;
  }
  
  private String url;
  
  public String getUrl() {
	  return this.url;
  }
  
  public void setUrl(String newUrl) {
	  this.url = newUrl;
  }
  
  
}