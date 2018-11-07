package br.com.rian.sysmanager.validator;

/**
 * @see Implementacao realizada com base em artigos publicados sobre Java 8 validator. Referencia  @author Dragon Warrior
 */
public class GenericValidationResult {

	private boolean valid;

	public boolean isValid() {
		
		return valid;
	}

	public static GenericValidationResult ok() {
		
		return new GenericValidationResult(true);
	}

	private GenericValidationResult(boolean valid) {
		
		this.valid = valid;
	}

	public static GenericValidationResult fail() {
		
		return new GenericValidationResult(false);
	}

}