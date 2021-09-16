package romanNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RomanNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RomanNumberConstraint {
    String message() default "Invalid Roman number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
