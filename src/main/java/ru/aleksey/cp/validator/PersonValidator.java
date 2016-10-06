package ru.aleksey.cp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.aleksey.cp.model.Person;

/**
 * Created by Алексей on 05.10.2016.
 */
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"fam","valIsEmpty","Значение не задано!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"im","valIsEmpty","Значение не задано!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"otch","valIsEmpty","Значение не задано!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"passport","valIsEmpty","Значение не задано!");

    }
}
