package ru.aleksey.cp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.aleksey.cp.model.Payment;

/**
 * Created by Алексей on 04.10.2016.
 */
public class PaymentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Payment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Payment pmt = (Payment)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "summ", "emptyOrWhitespaces", "Введено пустое значение");
    }
}
