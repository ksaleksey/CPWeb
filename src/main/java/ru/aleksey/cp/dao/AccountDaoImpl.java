package ru.aleksey.cp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.aleksey.cp.model.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.hibernate.SessionFactory;
import ru.aleksey.cp.model.Person;


/**
 * Created by Алексей on 29.09.2016.
 */

@Repository
public class AccountDaoImpl implements AccountDao{


    private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);




    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Account addAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(account);

        //todo: облагородить этот момент повозможности
        Query q1 = session.createQuery("select accnum from Account where id = :accId");
        q1.setInteger("accId", account.getId());
        Integer accnum = (Integer)q1.uniqueResult();
        account.setAccnum(accnum);

        logger.info("New Account added: "+ account);
        return account;
    }

    @Override
    public Account findAccountByAccNum(Account account) {

        Account result = null;
        Session session = sessionFactory.getCurrentSession();


        String hql = "FROM Account WHERE ACCNUM = :accnum";
        Query q1 = session.createQuery(hql);
        q1.setParameter("accnum", account.getAccnum());
        result = (Account)q1.uniqueResult();



        return result;
    }

    @Override
    public Account findAccountByAddress(Account account) {
        Account result = null;
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Account WHERE ACCNUM = :address";
        Query q1 = session.createQuery(hql);
        q1.setParameter("address", account.getAddress());
        result = (Account)q1.uniqueResult();
        return result;
    }

    @Override
    public List<Account> findAccountByFIO(Person person) {

        List<Account> result = null;
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Account a join a.person pers WHERE pers.fam = :fam and " +
                "pers.im = :im and pers.otch = :otch";
        Query q1 = session.createQuery(hql);
        q1.setParameter("fam", person.getFam());
        q1.setParameter("im", person.getIm());
        q1.setParameter("otch", person.getOtch());
        List results = q1.list();

        result = new ArrayList<Account>(results);

        return result;
    }

    @Override
    public List<Account> findAccountByParams(Account account, Person person) {
        List<Account> result = null;
        Session session = sessionFactory.getCurrentSession();

        StringBuffer hql = new StringBuffer("FROM Account a join a.person pers WHERE 1=1");

        Map<String, Object> params = new HashMap<String, Object>();
        if(account.getAddress()!=null){
            hql.append(" and a.address = :address");
            params.put("address", account.getAddress());
        }

        if(person.getFam()!=null){
            hql.append(" and pers.fam = :fam");
            params.put("fam", person.getFam());
        }

        if(person.getIm()!=null){
            hql.append(" and pers.im = :im");
            params.put("im", person.getIm());
        }

        if(person.getOtch()!=null){
            hql.append(" and pers.otch = :otch");
            params.put("otch", person.getOtch());
        }

        if(params.size()>0) {
            Query q1 = session.createQuery(hql.toString());


            for (Map.Entry<String, Object> entry : params.entrySet()) {
                q1.setParameter(entry.getKey(), entry.getValue());
            }

            List results = q1.list();

            result = new ArrayList<Account>(results);
        }

        return result;

    }

}
