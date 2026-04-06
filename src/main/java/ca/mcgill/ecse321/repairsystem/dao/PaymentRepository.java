package ca.mcgill.ecse321.repairsystem.dao;

import ca.mcgill.ecse321.repairsystem.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "payment_data", path = "payment_data")
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    List<Payment> findByStatus(Payment.PaymentStatus status);
    Optional<Payment> findById(int id);
    List<Payment> findAll();

    /**
     * Custom query to find a Payment by its associated appointment ID.
     *
     * @param appointmentId the ID of the appointment
     * @return an Optional containing the Payment if found, otherwise empty
     */
    Optional<Payment> findByAppointmentId(int appointmentId);
}
