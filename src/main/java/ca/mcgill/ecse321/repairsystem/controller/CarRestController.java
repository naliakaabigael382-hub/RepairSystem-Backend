package ca.mcgill.ecse321.repairsystem.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.repairsystem.dto.*;
import ca.mcgill.ecse321.repairsystem.model.*;
import ca.mcgill.ecse321.repairsystem.model.Car.CarType;
import ca.mcgill.ecse321.repairsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class CarRestController {

	@Autowired
	private CarService carService;

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = { "/cars/{customerId}", "/cars/{customerId}/" })
	public List<CarDto> getCarsByCustomer(@PathVariable("customerId") String customerId) {
		Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
		List<CarDto> carsDto = new ArrayList<CarDto>();
		for (Car car : carService.getCarsByCustomer(customer)) {
			carsDto.add(Converter.convertToDto(car));
		}
		return carsDto;
	}

	@PostMapping(value = { "/car/{customerId}", "/car/{customerId}/" })
	public CarDto createCar(@PathVariable("customerId") String customerId,
							@RequestParam String carType,
							@RequestParam String winterTires,
							@RequestParam String numOfKilometers) throws IllegalArgumentException {
		try {
			Customer customer = customerService.getCustomerById(Integer.parseInt(customerId));
			CarType type = CarType.valueOf(carType.toUpperCase()); // Ensure correct enum parsing
			boolean winterTiresValue = Boolean.parseBoolean(winterTires);
			int kilometers = Integer.parseInt(numOfKilometers);

			Car car = carService.createCar(type, winterTiresValue, kilometers, customer);
			return Converter.convertToDto(car);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid input data. Please check the input values for car creation.", e);
		} catch (Exception e) {
			throw new RuntimeException("Error while creating car.", e);
		}
	}
}
