package br.com.rian.sysmanager.validator;


/**
 * 
 * @author rian
 * @see Implementacao realizada com base em artigos publicados sobre Java 8 validator. Referencia  @author Dragon Warrior 
 * 
 */
public class ValidatorUtil {
	
    public static final Validation <String> NOTNULL_STRING = GenericValidation.from(s -> s != null);
    
    public static final Validation <String> NOTEMPTY_STRING = GenericValidation.from(s -> !s.isEmpty());
    
    public static final Validation <Long> NOTNULL_LONG = GenericValidation.from(s -> s != null);
    
    public static final Validation <Integer> NOTNULL_INTEGRE = GenericValidation.from(s -> s != null);
    
    public static final Validation <Long> MAIOR_THAN_LONG_ZERO = GenericValidation.from(s -> s > 0);
    
    public static final Validation <Integer> MAIOR_THAN_INTEGER_ZERO = GenericValidation.from(s -> s > 0);
}