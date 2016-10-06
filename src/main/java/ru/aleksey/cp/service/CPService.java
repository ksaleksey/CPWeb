package ru.aleksey.cp.service;

import ru.aleksey.cp.model.Account;
import ru.aleksey.cp.model.Payment;
import ru.aleksey.cp.model.Person;

import java.util.List;
import java.util.Map;

/**
 * Created by Алексей on 30.09.2016.
 */
public interface CPService {

    public Account addAccount(Account account, Person person);
    public List<Account> findAccountByFIO(Person person);
    public Account findAccountByAddress(Account account);
    public List<Account> findAccountByParams(Map<String,String> params);
    public Account findAccountByAccNum(Account account);
    public Payment addPayment(Payment payment);



}
