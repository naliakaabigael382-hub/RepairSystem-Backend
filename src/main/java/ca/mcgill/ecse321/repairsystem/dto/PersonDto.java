package ca.mcgill.ecse321.repairsystem.dto;

public class PersonDto {

	private String name;
	private String password;
	private int userId;
	private long phone;
	private String email;

	public PersonDto(String aName, int id,String aPassword, long aPhone, String aEmail)
	{
		name = aName;
		password = aPassword;
		userId = id;
		phone = aPhone;
		email = aEmail;
	}
	
	public PersonDto(int id) {	
		this.userId = id;
	}
	
	public PersonDto() {	
		
	}
	
	public int getId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public long getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
