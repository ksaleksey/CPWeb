package ru.aleksey.cp.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ru.aleksey.cp.model.Account;
import org.springframework.validation.Validator;
import ru.aleksey.cp.model.Payment;
import ru.aleksey.cp.service.CPService;


/**
 * Created by Алексей on 04.10.2016.
 */
public class AccountValidator implements Validator {

    private CPService service;

    @Autowired(required = true)
    @Qualifier(value = "cpservice")
    public void setService(CPService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Account.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {

        Account acc = (Account)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accnum", "accIsEmpty", new Object[]{"'account'"}, "Введено пустое значение!" );
        if(errors.hasErrors()){
            return;
        }

    }
}