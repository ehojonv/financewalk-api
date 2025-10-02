package br.com.fiap.financewalk.validation;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IconValidator implements ConstraintValidator<Icon, String> {

    private final List<String> validIcons = List.of(
        "Dices",
        "Apple",
        "Bus",
        "Train",
        "Book",
        "Banknote"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.isBlank() || validIcons.contains(value) ;
    }

}
