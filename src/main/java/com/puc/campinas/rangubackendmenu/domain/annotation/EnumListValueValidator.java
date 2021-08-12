package com.puc.campinas.rangubackendmenu.domain.annotation;

import java.util.List;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class EnumListValueValidator implements ConstraintValidator<EnumValidator, List<String>> {

  private EnumValidator annotation;

  @Override
  public void initialize(EnumValidator annotation) {
    this.annotation = annotation;
  }

  @Override
  public boolean isValid(List<String> lsValueForValidation,
      ConstraintValidatorContext constraintValidatorContext) {
    Boolean result = Boolean.TRUE;

    Object[] enumValues = this.annotation.enumClass().getEnumConstants();

    if (CollectionUtils.isEmpty(lsValueForValidation)) {
      result = !this.annotation.required();
    } else {
      if (Objects.nonNull(enumValues)) {
        for (int i = 0; i < lsValueForValidation.size() && result; i++) {
          String valueForValidation = lsValueForValidation.get(i);
          result = this.checkEnumValue(valueForValidation, enumValues);
        }
      }
    }
    return result;
  }

  private Boolean checkEnumValue(String valueForValidation, Object[] enumValues) {
    Boolean ret = Boolean.FALSE;
    for (int j = 0; j < enumValues.length && !ret; j++) {
      Object enumValue = enumValues[j];
      if ((!this.annotation.required()
          || (this.annotation.required() && !StringUtils.isEmpty(valueForValidation))) && (
          valueForValidation
              .equalsIgnoreCase(enumValue.toString()) ||
              this.annotation.ignoreCase() && valueForValidation
                  .equalsIgnoreCase(enumValue.toString()))) {
        ret = Boolean.TRUE;
      }
    }
    return ret;
  }


}
