package br.com.rian.sysmanager.bo;

import br.com.rian.sysmanager.entity.Identificavel;
import br.com.rian.sysmanager.exception.SistemaException;

/**
 * 
 * @author rian
 *
 * @param <T>
 */
public interface SysManagerBO<T extends Identificavel<?>> {

	public void validate(T entity) throws SistemaException;


}
