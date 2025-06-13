package com.locadora.project.util;

import java.util.Set;
import jakarta.validation.*;

public class ValidatorUtil<T> {
	private final Validator validator;
	
	public ValidatorUtil() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}
	
	public Integer validate(T dto, Class<?> group) {
		Set<ConstraintViolation<T>> violations = validator.validate(dto, group);
		
		if (!violations.isEmpty()) {
			for (ConstraintViolation<T> violation : violations) {
				System.out.println("Erro: " + violation.getMessage());
			}
			return 1;
		}
		return 0;
	}
	
	public Integer validate_field(T dto, String fieldName) {
		Set<ConstraintViolation<T>> violations = validator.validateProperty(dto, fieldName);
		
		if (!violations.isEmpty()) {
			for (ConstraintViolation<T> violation : violations) {
				System.out.println("Erro: " + violation.getMessage());
			}
			return 1;
		}
		return 0;
	}
}