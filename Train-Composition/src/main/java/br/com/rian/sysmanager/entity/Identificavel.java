package br.com.rian.sysmanager.entity;
import java.io.Serializable;

/**
 * @author rian
 * @param <T>
 */
public interface Identificavel<T> extends Serializable {
	T getCodigo();
	
	void setCodigo(T codigo);
	
}
