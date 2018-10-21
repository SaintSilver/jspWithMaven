package core.support;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

public class MyValidatorFactory {

	public static Validator createValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}
}
