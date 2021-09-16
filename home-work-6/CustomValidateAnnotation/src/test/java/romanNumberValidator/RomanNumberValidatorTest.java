package romanNumberValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumberValidatorTest {
    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void givenCorrectNumber_thenValidationSuccess() {
        RomanNumber number = new RomanNumber("LXXX");

        Set<ConstraintViolation<RomanNumber>> violations = validator.validate(number);
        assertEquals(0, violations.size());

    }

    @Test
    public void givenUnCorrectNumber_thenValidationFails() {
        RomanNumber number = new RomanNumber("IIXL");

        Set<ConstraintViolation<RomanNumber>> violations = validator.validate(number);
        assertEquals(1, violations.size());

    }
}

class RomanNumber{
    @RomanNumberConstraint
    private String value;

    public RomanNumber(String value) {
        this.value = value;
    }
}