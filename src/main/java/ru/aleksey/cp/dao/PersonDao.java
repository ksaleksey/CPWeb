package ru.aleksey.cp.dao;

import ru.aleksey.cp.model.Person;

import java.util.List;

/**
 * Created by Алексей on 03.10.2016.
 */
public interface PersonDao {
        public Person addPerson(Person person);
        public Person findPersonByPassport(Person person);
        public List<Person> findPersonByFIO(Person person);
}
