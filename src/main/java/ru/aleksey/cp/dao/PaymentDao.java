package ru.aleksey.cp.dao;

import ru.aleksey.cp.model.Account;
import ru.aleksey.cp.model.Payment;

import java.util.List;

/**
 * Created by Алексей on 02.10.2016.
 */
public interface PaymentDao {
    public Payment addPayment(Payment payment);
    public List<Payment> findPaymentsByAccount(Account account);
}
