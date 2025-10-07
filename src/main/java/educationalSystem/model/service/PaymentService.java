package educationalSystem.model.service;

import educationalSystem.model.entity.Payment;
import educationalSystem.model.repository.PaymentRepository;
import lombok.Getter;

import java.util.List;

public class PaymentService implements Service<Payment, Integer> {

    @Getter
    private static PaymentService service =  new PaymentService();

    private PaymentService() {
    }

    @Override
    public void save(Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.save(payment);
        }
    }

    @Override
    public void edit(Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.edit(payment);
        }
    }

    @Override
    public void delete(Integer paymentCode) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.delete(paymentCode);
        }
    }

    @Override
    public List<Payment> findAll() throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findAll();
        }
    }

    @Override
    public Payment findById(Integer paymentCode) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findById(paymentCode);
        }
    }
}
