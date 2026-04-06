package ca.mcgill.ecse321.repairsystem.dto;

public class AdministrativeAssistantDto extends PersonDto {
	
	public AdministrativeAssistantDto(String aName, int id, String aPassword, long aPhone, String aEmail) {
		super(aName,id, aPassword, aPhone, aEmail);
	}
	
	public AdministrativeAssistantDto(int id) {
		super(id);
	}
	
	public AdministrativeAssistantDto() {
		
	}
	
}
