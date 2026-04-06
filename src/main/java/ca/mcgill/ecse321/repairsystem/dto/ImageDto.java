package ca.mcgill.ecse321.repairsystem.dto;

public class ImageDto {
	private AppointmentDto appointment;
	private int imageId;
	private String url;
	
	public ImageDto(int id, String url, AppointmentDto a) {
		  this.imageId = id;
		  this.url = url;
		  this.appointment = a;
	}
	
	public ImageDto(int id) {
		this.imageId = id;
	}
	
	public ImageDto() {
		
	}
	 
	public AppointmentDto getAppointment() {
	    return appointment;
	}
	
	public void setAppointment(AppointmentDto appointment)
	{
		this.appointment = appointment;
	}
	 
	public int getId() {
		return imageId;
	}
	  
	public String getUrl() {
		return url;
	}
	  
}
