package romanNumberValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RomanNumberValidator implements ConstraintValidator<RomanNumberConstraint, String> {

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext constraintValidatorContext) {
        return fieldValue != null && fieldValue.matches("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
}
