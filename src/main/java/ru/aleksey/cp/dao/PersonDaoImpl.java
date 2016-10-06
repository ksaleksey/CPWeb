package ru.aleksey.cp.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.aleksey.cp.model.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 03.10.2016.
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);


    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Person addPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(person);

        return person;
    }

    @Override
    public Person findPersonByPassport(Person person) {

        Person result = null;

        Session session = sessionFactory.getCurrentSession();

        Query q1 = session.createQuery("select id from Person where passport = :pasp");

        q1.setLong("pasp", person.getPassport());
        Integer personId = (Integer) q1.uniqueResult();

        if(personId!=null){

            result = (Person)session.get(Person.class, personId);
        }

        return result;
    }

    @Override
    public List<Person> findPersonByFIO(Person person) {
        List<Person> result = null;

        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Person WHERE fam = :fam and im = :im and otch = :otch";
        Query q1 = session.createQuery(hql);
        q1.setParameter("fam", person.getFam());
        q1.setParameter("im",person.getIm());
        q1.setParameter("otch",person.getOtch());
        List results = q1.list();

        result = new ArrayList<Person>(results);
        return result;
    }
}
