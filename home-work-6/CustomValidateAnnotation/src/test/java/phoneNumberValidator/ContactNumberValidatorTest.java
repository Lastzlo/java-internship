package phoneNumberValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ContactNumberValidatorTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void givenCorrectNumber_thenValidationSuccess() {
        User user = new User("0971234567");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());

    }

    @Test
    public void givenUnCorrectNumber_thenValidationFails() {
        User user = new User("09712");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());

    }


}

class User {
    @ContactNumberConstraint
    private String phone;

    public User(String phone) {
        this.phone = phone;
    }
}