package ca.mcgill.ecse321.repairsystem.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.dao.AppointmentRepository;
import ca.mcgill.ecse321.repairsystem.dao.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;

	////////////////////SERVICE IMAGE METHODS //////////////////// 
	
	
	/**
	 * Creating service object
	 * @param url
	 * @param a
	 * @return
	 */
	@Transactional
	public Image createImage(String url, Appointment a) {
		if(url == null || url.trim().length() == 0) throw new IllegalArgumentException("Image url cannot be empty!");
		if(a == null) throw new IllegalArgumentException("Image appointment cannot be empty!");

		int id = url.hashCode();
		Image i = new Image(id, url, a);
		a.addImage(i);
		imageRepository.save(i);
		appointmentRepository.save(a);
		return i;
	}
	
	/**
	 * Obtain all images associated to an appointment
	 * @param Appointment a
	 * @return List of images
	 */
	@Transactional List<Image> getImagesByAppointment(Appointment a) {
		List<Image> i = toList(imageRepository.findByAppointment(a));
		return i;
	}

	/**
	 * Obtain all images in the database
	 * @return list of images
	 */
	@Transactional
	public List<Image> getAllImages() {
		return toList(imageRepository.findAll());
	}
	
	/**
	 * Obtain an images by searching by url 
	 * @param url
	 * @return image object
	 */
	@Transactional
	public Image getImageByUrl(String url) {
		return imageRepository.findByUrl(url);
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
