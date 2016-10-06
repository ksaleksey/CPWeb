package ru.aleksey.cp.dao;

import ru.aleksey.cp.model.Account;
import ru.aleksey.cp.model.Person;

import java.util.List;

/**
 * Created by Алексей on 29.09.2016.
 */
public interface AccountDao {

    public Account addAccount(Account account);
    public Account findAccountByAccNum(Account account);
    public Account findAccountByAddress(Account account);
    public List<Account> findAccountByFIO(Person person);
    public List<Account> findAccountByParams(Account account, Person person);
}
