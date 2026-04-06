package ca.mcgill.ecse321.repairsystem.service;

import ca.mcgill.ecse321.repairsystem.model.Payment;
import ca.mcgill.ecse321.repairsystem.dao.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment save(Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findByAppointmentId(payment.getAppointmentId());
        if (existingPayment.isPresent()) {
            throw new IllegalArgumentException("Payment has already been made for this appointment.");
        }
        return paymentRepository.save(payment);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> findById(int id) {
        return paymentRepository.findById(id);
    }

    public Payment update(int id, Payment updatedPayment) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);  // Fetch the payment details
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setAmount(updatedPayment.getAmount());
            payment.setStatus(updatedPayment.getStatus());
            payment.setReceipt(updatedPayment.getReceipt());
            // Update appointmentId directly as the appointment is now referenced by its ID
            payment.setAppointmentId(updatedPayment.getAppointmentId());
            return paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

    public void delete(int id) {
        paymentRepository.deleteById(id);
    }

    /**
     * Custom query to find a Payment by its associated appointment ID.
     *
     * @param appointmentId the ID of the appointment
     * @return an Optional containing the Payment if found, otherwise empty
     */
    public Optional<Payment> findByAppointmentId(int appointmentId) {
        return paymentRepository.findByAppointmentId(appointmentId);
    }

    /**
     * Update the receipt for an existing payment.
     *
     * @param paymentId the ID of the payment to update
     * @param receiptUrl the new receipt URL
     */
    public void updateReceipt(int paymentId, String receiptUrl) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setReceipt(receiptUrl);
            paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id: " + paymentId);
        }
    }
}
