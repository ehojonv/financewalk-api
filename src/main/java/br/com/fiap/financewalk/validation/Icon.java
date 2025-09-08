package br.com.fiap.financewalk.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IconValidator.class)
public @interface Icon {

    	String message() default "{icon.invalid}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
