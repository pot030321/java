package dajava.dacs.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidUsernameValidator.class)
public @interface ValidUsername {
    String message() default "Tên đăng nhập không hợp lê";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};

}
