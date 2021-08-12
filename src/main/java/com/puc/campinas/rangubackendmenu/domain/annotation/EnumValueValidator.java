package com.puc.campinas.rangubackendmenu.domain.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValidator, String> {

  private EnumValidator annotation;

  @Override
  public void initialize(EnumValidator annotation) {
    this.annotation = annotation;
  }

  @Override
  public boolean isValid(String valueForValidation,
      ConstraintValidatorContext constraintValidatorContext) {
    boolean result = false;

    Object[] enumValues = this.annotation.enumClass().getEnumConstants();

    if (enumValues != null) {
      for (Object enumValue : enumValues) {
        if (valueForValidation == null || valueForValidation.isEmpty()) {
          if (this.annotation.required()) {
            result = false;
          } else {
            result = true;
          }
          break;
        }
        if (valueForValidation.equals(enumValue.toString()) || (this.annotation.ignoreCase()
            && valueForValidation.equalsIgnoreCase(enumValue.toString()))) {
          result = true;
          break;
        }
      }
    }

    return result;
  }
}
