package ca.mcgill.ecse321.repairsystem.model;

public class PaymentStatusResponse {

    private boolean paymentDone;
    private String status;
    private String receipt;

    // Constructor
    public PaymentStatusResponse(boolean paymentDone, String status, String receipt) {
        this.paymentDone = paymentDone;
        this.status = status;
        this.receipt = receipt;
    }

    // Getters and setters
    public boolean isPaymentDone() {
        return paymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        this.paymentDone = paymentDone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
