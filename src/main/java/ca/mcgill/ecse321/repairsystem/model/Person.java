package ca.mcgill.ecse321.repairsystem.model;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Entity;

@Entity
@Inheritance(strategy =InheritanceType.JOINED)
public abstract class Person{

	private String name;
	private String password;
	private int userId;
	private long phone;
	private String email;
	
	public Person(String aName, int id,String aPassword, long aPhone, String aEmail)
	{
		name = aName;
		password = aPassword;
		userId = id;
		phone = aPhone;
		email = aEmail;
	}
	
	public Person() {
	}

	@Id
	public int getId()
	{
		return this.userId;
	}

	public void setId(int aId)
	{
		this.userId = aId;
	}
	

	public String getName()
	{
		return name;
	}
	

	public void setName(String aName){
		this.name = aName;
	}
	
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String aPassword)
	{
		password = aPassword;

	}
	

	public long getPhone()
	{
		return phone;
	}

	public void setPhone(long aPhone)
	{
		phone = aPhone;

	}

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String aEmail)
	{
		email = aEmail;
	}

}
