package ca.mcgill.ecse321.repairsystem.controller;

import ca.mcgill.ecse321.repairsystem.model.Appointment;
import ca.mcgill.ecse321.repairsystem.model.Payment;
import ca.mcgill.ecse321.repairsystem.service.AppointmentService;
import ca.mcgill.ecse321.repairsystem.service.PaymentService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://192.168.100.39:8087")

public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AppointmentService appointmentService;

    private static final String RECEIPT_STORAGE_PATH = "src/main/resources/receipts/"; //
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @PostConstruct
    public void createReceiptDirectory() {
        File dir = new File(RECEIPT_STORAGE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @PostMapping
    public ResponseEntity<?> makePayment(@Valid @RequestBody Payment payment) {
        if (payment.getAppointmentId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment ID must not be null.");
        }

        if (payment.getEmail() == null || !EMAIL_PATTERN.matcher(payment.getEmail()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A valid email address must be provided.");
        }

        Optional<Appointment> optionalAppointment = appointmentService.findById(payment.getAppointmentId());
        if (optionalAppointment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid appointment ID provided.");
        }

        Optional<Payment> existingPayment = paymentService.findByAppointmentId(payment.getAppointmentId());
        if (existingPayment.isPresent()) {
            String receiptUrl = existingPayment.get().getReceipt() != null
                    ? existingPayment.get().getReceipt()
                    : generateReceiptLink(existingPayment.get().getId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Payment already made. Receipt available here: " + receiptUrl);
        }

        Payment newPayment = new Payment();
        newPayment.setAmount(payment.getAmount());
        newPayment.setStatus(Payment.PaymentStatus.COMPLETED);
        newPayment.setEmail(payment.getEmail());
        newPayment.setPaymentMethod(payment.getPaymentMethod());
        newPayment.setAddress(payment.getAddress());
        newPayment.setAppointmentId(payment.getAppointmentId());

        try {
            Payment savedPayment = paymentService.save(newPayment);
            String receiptUrl = generateReceipt(savedPayment);
            savedPayment.setReceipt(receiptUrl);
            paymentService.updateReceipt(savedPayment.getId(), receiptUrl);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Payment successful. Receipt available here: " + receiptUrl);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred due to database constraints. Please try again later.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the payment. Please try again later.");
        }
    }

    @GetMapping("/receipts/{paymentId}")
    public ResponseEntity<Resource> downloadReceipt(@PathVariable int paymentId) {
        Optional<Payment> paymentOpt = paymentService.findById(paymentId);
        if (paymentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Payment payment = paymentOpt.get();
        String fileName = "receipt_" + paymentId + ".pdf";
        String filePath = RECEIPT_STORAGE_PATH + fileName;

        try {
            Path pdfPath = Paths.get(filePath);
            if (!Files.exists(pdfPath)) {
                generatePdfReceipt(payment);
            }

            Resource resource = new UrlResource(pdfPath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private void generatePdfReceipt(Payment payment) throws IOException {
        String filePath = RECEIPT_STORAGE_PATH + "receipt_" + payment.getId() + ".pdf";
        PdfWriter writer = new PdfWriter(filePath);
        try (PdfDocument pdf = new PdfDocument(writer); Document document = new Document(pdf)) {
            document.add(new Paragraph("Receipt"));
            document.add(new Paragraph("-------------------"));
            document.add(new Paragraph("Receipt ID: " + payment.getId()));
            document.add(new Paragraph("Amount: " + payment.getAmount()));
            document.add(new Paragraph("Status: " + payment.getStatus()));
            document.add(new Paragraph("Date: " + LocalDateTime.now()));
            document.add(new Paragraph("Payment Method: " + payment.getPaymentMethod()));
            document.add(new Paragraph("Address: " + payment.getAddress()));
            document.add(new Paragraph("Email: " + payment.getEmail()));
        }
    }

    private String generateReceipt(Payment payment) {
        String receiptUrl = generateReceiptLink(payment.getId());
        return String.format("Receipt ID: %d\nAmount: %.2f\nStatus: %s\nDate: %s\nPayment Method: %s\nAddress: %s\nEmail: %s\nLink: %s\n",
                payment.getId(), payment.getAmount(), payment.getStatus(), LocalDateTime.now(),
                payment.getPaymentMethod(), payment.getAddress(), payment.getEmail(), receiptUrl);
    }

    private String generateReceiptLink(int paymentId) {
        return "http://192.168.100.39:3087/payments/receipts/" + paymentId; // Replace with your actual URL and port
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable int id, @RequestBody Payment updatedPayment) {
        Optional<Payment> existingPayment = paymentService.findById(id);

        if (existingPayment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found with ID: " + id);
        }

        Payment payment = existingPayment.get();
        if (updatedPayment.getStatus() != null) {
            payment.setStatus(updatedPayment.getStatus());
        }
        if (updatedPayment.getReceipt() != null) {
            payment.setReceipt(updatedPayment.getReceipt());
        }
        if (updatedPayment.getEmail() != null && EMAIL_PATTERN.matcher(updatedPayment.getEmail()).matches()) {
            payment.setEmail(updatedPayment.getEmail());
        }
        if (updatedPayment.getAppointmentId() != null) {
            Optional<Appointment> optionalAppointment = appointmentService.findById(updatedPayment.getAppointmentId());
            if (optionalAppointment.isPresent()) {
                payment.setAppointmentId(updatedPayment.getAppointmentId());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid appointment ID provided.");
            }
        }

        Payment savedPayment = paymentService.save(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable int id) {
        Optional<Payment> existingPayment = paymentService.findById(id);
        if (existingPayment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found with ID: " + id);
        }
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllPayments() {
        List<Payment> payments = paymentService.findAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable int id) {
        Optional<Payment> payment = paymentService.findById(id);
        if (payment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found with ID: " + id);
        }
        return ResponseEntity.ok(payment.get());
    }


    // Endpoint to check payment status and receipt link
    @GetMapping("/status/{appointmentId}")
    public ResponseEntity<?> checkPaymentStatus(@PathVariable int appointmentId) {
        Optional<Payment> payment = paymentService.findByAppointmentId(appointmentId);
        if (payment.isPresent()) {
            Payment p = payment.get();
            return ResponseEntity.ok(new PaymentStatusResponse(
                    true, p.getId(), p.getStatus().toString(), p.getReceipt()
            ));
        } else {
            return ResponseEntity.ok(new PaymentStatusResponse(false, null, "Pending", null));
        }
    }

    // Additional class to structure the response
    public static class PaymentStatusResponse {
        private boolean paymentDone;
        private Integer paymentId;  // Include paymentId
        private String status;
        private String receiptLink;

        // Constructor
        public PaymentStatusResponse(boolean paymentDone, Integer paymentId, String status, String receiptLink) {
            this.paymentDone = paymentDone;
            this.paymentId = paymentId;
            this.status = status;
            this.receiptLink = receiptLink;
        }

        public boolean isPaymentDone() {
            return paymentDone;
        }

        public Integer getPaymentId() {
            return paymentId;
        }

        public String getStatus() {
            return status;
        }

        public String getReceiptLink() {
            return receiptLink;
        }
    }

}
