package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.repairsystem.dao.*;
import ca.mcgill.ecse321.repairsystem.model.*;

@Service
public class AdministrativeAssistantService {
	@Autowired
	private AdministrativeAssistantRepository administrativeAssistantRepository;

	@Transactional
	public AdministrativeAssistant createAdmin(String aName, String aPassword, long aPhone, String aEmail) {
		
		if(aName == null || aName.trim().length() == 0)
		{
			throw new IllegalArgumentException("Administrative assistant name cannot be empty!");
				
		}else if (aPassword == null || aPassword.trim().length() == 0)
		{
			throw new IllegalArgumentException("Administrative assistant password cannot be empty!");
		}else if (aEmail == null || aEmail.trim().length() == 0)
		{
			throw new IllegalArgumentException("Administrative assistant email cannot be empty!");
		}
		int id = aEmail.hashCode();
		AdministrativeAssistant admin = new AdministrativeAssistant(aName, id, aPassword, aPhone, aEmail);
		administrativeAssistantRepository.save(admin);
		return admin;
	}
	
	/**
	 * Edits the name, password, phone and email  of an administrator
	 * @param admin
	 * @param name
	 * @param password
	 * @param phone
	 * @param email
	 * @return
	 */
	@Transactional
	public AdministrativeAssistant editAdmin(String email , String name, String password, long phone) {
		AdministrativeAssistant admin = getAdminByEmail(email);
		admin.setName(name);
		admin.setPassword(password);
		admin.setPhone(phone);
		return admin;
	}

	/**
	 * Getter method to get the administrator given the id
	 * @param id
	 * @return
	 */
	@Transactional 
	public AdministrativeAssistant getAdminById(int id) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findById(id);
		return admin;
	}

	@Transactional 
	public List<AdministrativeAssistant> getAdminsByName(String name) {
		List<AdministrativeAssistant> admins = toList(administrativeAssistantRepository.findByName(name));
		return admins;
	}

	/**
	 * Getter method to get the administrator object given a phone number
	 * @param number
	 * @return
	 */
	@Transactional 
	public AdministrativeAssistant getAdminByNumber(long number) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findByPhone(number);
		return admin;
	}

	/**
	 * Getter method to get the administrator object given the email
	 * @param email
	 * @return
	 */
	@Transactional 
	public AdministrativeAssistant getAdminByEmail(String email) {
		AdministrativeAssistant admin = administrativeAssistantRepository.findByEmail(email);
		return admin;
	}

	/**
	 * Getter method to get all the administrators in the database
	 * @return
	 */
	@Transactional
	public List<AdministrativeAssistant> getAllAdmins() {
		return toList(administrativeAssistantRepository.findAll());
	}

	/* 
	 * helper method
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}