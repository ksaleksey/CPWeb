package ru.aleksey.cp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.aleksey.cp.model.Account;
import ru.aleksey.cp.model.Payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Алексей on 02.10.2016.
 */
@Repository
public class PaymentDaoImpl implements PaymentDao {


        private static final Logger logger = LoggerFactory.getLogger(PaymentDaoImpl.class);


        private SessionFactory sessionFactory;


        public void setSessionFactory(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }


    @Override
    public Payment addPayment(Payment payment) {

        Session session = sessionFactory.getCurrentSession();

        session.persist(payment);

        //todo: облагородить этот момент повозможности
        Query q1 = session.createQuery("select pmt_time from Payment where id = :pmtId");
        q1.setInteger("pmtId", payment.getId());
        Date pmt_date = (Date)q1.uniqueResult();
        payment.setPmt_time(pmt_date);

        logger.info("Payment added: "+payment);

        return payment;

    }

    @Override
    public List<Payment> findPaymentsByAccount(Account account) {
        List<Payment> result = null;

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Payment WHERE ACCOUNT_ID = :accid ";
        Query q1 = session.createQuery(hql);
        q1.setParameter("accid", account.getId());
        List results = q1.list();

        result = new ArrayList<Payment>(results);
        return result;
    }
}
