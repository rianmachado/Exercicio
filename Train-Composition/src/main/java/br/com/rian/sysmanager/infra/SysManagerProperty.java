package br.com.rian.sysmanager.infra;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author rian
 */
public class SysManagerProperty {

	
	
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(SysManagerProperty.class);
	
	private static ResourceBundle bundle = null;

	/**
	 * 
	 * @return
	 */
	public static ResourceBundle bundle(String path_bundle, Locale locale) {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(path_bundle, locale);
		}
		return bundle;
	}

}