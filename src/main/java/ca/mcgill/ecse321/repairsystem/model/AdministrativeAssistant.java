package ca.mcgill.ecse321.repairsystem.model;

import jakarta.persistence.Entity;


@Entity
public class AdministrativeAssistant extends Person
{
	
	public AdministrativeAssistant(String aName, int id, String aPassword, long aPhone, String aEmail) {
		super(aName,id, aPassword, aPhone, aEmail);
	}
	
	public AdministrativeAssistant() {
		// TODO Auto-generated constructor stub
	}

}