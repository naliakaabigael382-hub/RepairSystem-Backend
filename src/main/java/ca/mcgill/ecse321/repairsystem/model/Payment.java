package ca.mcgill.ecse321.repairsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Auto-incremented primary key

    @Min(value = 0, message = "Amount must be greater than or equal to zero")
    private double amount;

    @NotNull(message = "Status cannot be null")
    private PaymentStatus status;

    private String receipt;

    // New fields
    private String email;
    private String paymentMethod;
    private String address;

    @NotNull(message = "Appointment ID must not be null")
    private Integer appointmentId; // Use Integer to allow null values

    public Payment() {}

    public Payment(double amount, PaymentStatus status, String receipt, String email, String paymentMethod, String address, Integer appointmentId) {
        this.amount = amount;
        this.status = status;
        this.receipt = receipt;
        this.email = email;
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.appointmentId = appointmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public enum PaymentStatus { PENDING, COMPLETED, FAILED }
}
