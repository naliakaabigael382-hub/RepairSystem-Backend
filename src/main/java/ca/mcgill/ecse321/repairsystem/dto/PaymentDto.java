package ca.mcgill.ecse321.repairsystem.dto;

import ca.mcgill.ecse321.repairsystem.model.Payment.PaymentStatus;

public class PaymentDto {

    private int id;
    private double amount;
    private PaymentStatus status;
    private String receipt;
    private AppointmentDto appointment;

    // New fields
    private String email;
    private String paymentMethod;
    private String address;

    public PaymentDto() {}

    public PaymentDto(int id, double amount, String status, String receipt, Integer appointmentId, String email, String paymentMethod, String address) {
        this.id = id;
        this.amount = amount;
        this.status = PaymentStatus.valueOf(status);
        this.receipt = receipt;
        this.email = email;
        this.paymentMethod = paymentMethod;
        this.address = address;

        this.appointment = new AppointmentDto();
        this.appointment.setId(appointmentId);
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

    public AppointmentDto getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentDto appointment) {
        this.appointment = appointment;
    }

    public void setAppointmentId(Integer appointmentId) {
        if (this.appointment == null) {
            this.appointment = new AppointmentDto();
        }
        this.appointment.setId(appointmentId);
    }

    public Integer getAppointmentId() {
        return this.appointment != null ? this.appointment.getId() : null;
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
}
