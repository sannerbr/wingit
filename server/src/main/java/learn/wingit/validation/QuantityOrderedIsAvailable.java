package learn.wingit.validation;



import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {QuantityOrderedValidator.class})
public @interface QuantityOrderedIsAvailable {
    String message() default  "{Quantity ordered cannot be greater than quantity available}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
