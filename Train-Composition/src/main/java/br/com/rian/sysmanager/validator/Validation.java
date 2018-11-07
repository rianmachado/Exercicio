package br.com.rian.sysmanager.validator;

/**
 * @see Implementacao realizada com base em artigos publicados sobre Java 8 validator. Referencia  @author Dragon Warrior 
 */
@FunctionalInterface
public interface Validation<K> {

	public GenericValidationResult test(K param);

	default Validation<K> and(Validation<K> other) {
		
		return (param) -> {
			
			GenericValidationResult result = this.test(param);
			
			return !result.isValid() ? result : other.test(param);
		};
	}

}