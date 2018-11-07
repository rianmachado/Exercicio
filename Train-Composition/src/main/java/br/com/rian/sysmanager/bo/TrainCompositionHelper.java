package br.com.rian.sysmanager.bo;

import org.apache.log4j.Logger;

import br.com.rian.sysmanager.enums.CodigoErroSistema;
import br.com.rian.sysmanager.exception.SistemaException;
import br.com.rian.sysmanager.validator.ValidatorUtil;

/**
 * 
 * @author rian
 *
 */
public class TrainCompositionHelper {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(TrainCompositionHelper.class);

	/**
	 * 
	 * @param numbers
	 * @return
	 * @see Implementacao BubbleSort, referencia DEVMEDIA. Ate 1000 elementos o
	 *      BubbleSort é eficiente e realiza a ordenação de forma eficaz.
	 */
	public Integer[] sortAscending(final Integer... numbers) {
		boolean troca = true;
		
		int swap;
		
		while (troca) {
			
			troca = false;
			
			for (int i = 0; i < numbers.length - 1; i++) {
				
				if (numbers[i] > numbers[i + 1]) {
					
					swap = numbers[i];
					
					numbers[i] = numbers[i + 1];
					
					numbers[i + 1] = swap;
					
					troca = true;
				}
			}
		}
		
		return numbers;
	}


	/**
	 * 
	 * @param vagoes
	 * @param novoVagao
	 * @return
	 * @throws SistemaException
	 */
	public Integer[] validarNovoVagao(final Integer[] vagoes, final Integer novoVagao) throws SistemaException {

		if (vagoes == null || vagoes.length == 0) {
			
			throw new SistemaException(CodigoErroSistema.ERRO_PARAMETRO_COMPOSICAO_VAGOES_INVALIDA);
		}

		boolean validado = ValidatorUtil.NOTNULL_INTEGRE.and(ValidatorUtil.MAIOR_THAN_INTEGER_ZERO).test(novoVagao).isValid();
		
		if (!validado) {
			
			throw new SistemaException(CodigoErroSistema.ERRO_PARAMETRO_NUMERO_VAGAO_INVALIDO);
			
		}

		return new Integer[vagoes.length + 1];

	}

}
