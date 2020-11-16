package learn.wingit.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Constraint(validatedBy = DuplicatePlaneValidator.class)
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoDuplicatePlane {
    String message() default "{This plane already exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
