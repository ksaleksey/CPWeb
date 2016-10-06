package ru.aleksey.cp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksey.cp.dao.AccountDao;
import ru.aleksey.cp.dao.PaymentDao;
import ru.aleksey.cp.dao.PersonDao;
import ru.aleksey.cp.model.Account;
import ru.aleksey.cp.model.Payment;
import ru.aleksey.cp.model.Person;

import java.util.List;
import java.util.Map;

/**
 * Created by Алексей on 30.09.2016.
 */
@Service
public class CPServiceImpl implements CPService {



    private AccountDao accountDao;
    private PaymentDao paymentDao;
    private PersonDao personDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    public void setPaymentDao(PaymentDao paymentDao) { this.paymentDao = paymentDao; }
    public void setPersonDao(PersonDao personDao) { this.personDao = personDao; }

    @Override
    @Transactional()
    public Account addAccount(Account account, Person person) {
        Account result = null;

            //todo: проверить входные данные, обработать ошибки
            Person pers = personDao.findPersonByPassport(person);
            if (pers == null) {
                pers = personDao.addPerson(person);
            }



            account.setPerson(pers);


            result = accountDao.addAccount(account);
            // todo: обработать ошибки

        return result;
    }

    @Override
    @Transactional
    public Payment addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }

    @Override
    @Transactional
    public Account findAccountByAddress(Account account) {

       return accountDao.findAccountByAddress(account);
    }

    @Override
    @Transactional
    public List<Account> findAccountByParams(Map<String, String> params) {

        // заполняем в соответствии с заданием только поля ФИО и адрес
        Account account = new Account();
        account.setAddress(params.get("address"));
        Person person = new Person();
        person.setIm(params.get("im"));
        person.setFam(params.get("fam"));
        person.setOtch(params.get("otch"));

        return accountDao.findAccountByParams(account, person);
    }

    @Override
    @Transactional
    public Account findAccountByAccNum(Account account) {
        return accountDao.findAccountByAccNum(account);
    }

    @Override
    @Transactional
    public List<Account> findAccountByFIO(Person person) {

        return accountDao.findAccountByFIO(person);
    }
}
