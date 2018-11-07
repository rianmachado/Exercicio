package br.com.rian.sysmanager.exception;

import br.com.rian.sysmanager.enums.CodigoErro;

/**
 * 
 * @author rian
 *
 */
public class SistemaException extends Exception {

	private static final long serialVersionUID = -4485767351033230602L;
	
	private CodigoErro codigo; 

	/**
	 * 
	 * @param codigo
	 * @param tw
	 */
	public SistemaException(CodigoErro codigo, Throwable tw) {
		
		super(codigo.getIdBundle(),tw);
		
		this.codigo = codigo;
	}
	
	
	/**
	 * 
	 * @param codigo
	 */
	public SistemaException(CodigoErro codigo) {
		
		super(codigo.getIdBundle());
		
		this.codigo = codigo;
	}

	/**
	 * 
	 * @return
	 */
	public CodigoErro getCodigo() {
		
		return codigo;
	}
}
