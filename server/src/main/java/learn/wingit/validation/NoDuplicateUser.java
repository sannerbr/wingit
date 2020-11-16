package learn.wingit.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {DuplicateUserValidator.class})
@Documented
public @interface NoDuplicateUser {
    String message() default "{This username is already taken}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
