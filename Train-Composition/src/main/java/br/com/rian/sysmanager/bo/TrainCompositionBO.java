package br.com.rian.sysmanager.bo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import br.com.rian.sysmanager.entity.TrainComposition;
import br.com.rian.sysmanager.enums.CodigoErroSistema;
import br.com.rian.sysmanager.enums.Posicao;
import br.com.rian.sysmanager.exception.SistemaException;
import br.com.rian.sysmanager.validator.ValidatorUtil;

/**
 * 
 * @author rian
 *
 */
public class TrainCompositionBO implements SysManagerBO<TrainComposition> {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(TrainCompositionBO.class);

	private TrainCompositionHelper compositionHelper = new TrainCompositionHelper();

	/**
	 * 
	 * @param numbers
	 * @param number
	 * @return
	 * @throws SistemaException
	 */
	public Integer countNumbers(Integer numbers[], Integer number) throws SistemaException {
		if (numbers == null || numbers.length == 0) {
			
			throw new SistemaException(CodigoErroSistema.ERRO_PARAMETRO_COMPOSICAO_VAGOES_INVALIDA);
		}
		compositionHelper.sortAscending(numbers);
		
		Stream<Integer> streamNumbersFilter = Arrays.stream(numbers);
		
		List<Integer> resultado = streamNumbersFilter.filter(n -> n.compareTo(number) < 0).collect(Collectors.toList());
		
		return resultado.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.rian.sysmanager.bo.SysManagerBO#validate(br.com.rian.sysmanager.entity
	 * .Identificavel)
	 */
	@Override
	public void validate(TrainComposition entity) throws SistemaException {
		
		boolean validado = false;
		
		if (entity == null) {
			
			throw new SistemaException(CodigoErroSistema.ERRO_INTERNO);
			
		}
		validado = ValidatorUtil.NOTNULL_STRING.and(ValidatorUtil.NOTEMPTY_STRING).test(entity.getNome()).isValid();
		
		if (!validado) {
			
			throw new SistemaException(CodigoErroSistema.ERRO_PARAMETRO_TRAINCOMPOSITION_NOME_INVALIDO);
		}

		validado = ValidatorUtil.NOTNULL_LONG.and(ValidatorUtil.MAIOR_THAN_LONG_ZERO).test(entity.getCodigo()).isValid();
		
		if (!validado) {
			
			throw new SistemaException(CodigoErroSistema.ERRO_PARAMETRO_TRAINCOMPOSITION_CODIGO_INVALIDO);
			
		}
		
		validado = ValidatorUtil.NOTNULL_INTEGRE.and(ValidatorUtil.MAIOR_THAN_INTEGER_ZERO).test(entity.getNovoVagao()).isValid();
		
		if (!validado) {
			
			throw new SistemaException(CodigoErroSistema.ERRO_NOVO_VAGAO_INVALIDO);
			
		}
	}

	/**
	 * 
	 * @param vagoes
	 * @param posicaoAtracar
	 * @param novoVagao
	 * @throws SistemaException
	 */
	public Integer[] atracarVagao(final Integer[] vagoes, Posicao posicaoAtracar, final Integer novoVagao)
			throws SistemaException {

		if (vagoes == null || vagoes.length ==0 ) {
			
			throw new SistemaException(CodigoErroSistema.ERRO_PARAMETRO_COMPOSICAO_VAGOES_INVALIDA);
		}
		
		if(posicaoAtracar==null) {
			throw new SistemaException(CodigoErroSistema.ERRO_PARAMETRO_POSICAO_VAGAO_INVALIDO);
		}

		Integer[] vagoesSwap = compositionHelper.validarNovoVagao(vagoes, novoVagao);

		if (Posicao.ESQUERDA.name().equals(posicaoAtracar.name())) {
			
			for (int i = 0; i < vagoes.length; i++) {
				
				vagoesSwap[i + 1] = vagoes[i];
			}
			vagoesSwap[0] = novoVagao;
			
		} else if (Posicao.DIREIRA.name().equals(posicaoAtracar.name())) {
			
			for (int i = 0; i < vagoes.length; i++) {
				
				vagoesSwap[i] = vagoes[i];
			}
			
			vagoesSwap[(vagoesSwap.length - 1)] = novoVagao;
			
		}

		return vagoesSwap;

	}

	/**
	 * 
	 * @param compositionHelper
	 */
	public void setCompositionHelper(TrainCompositionHelper compositionHelper) {
		
		this.compositionHelper = compositionHelper;
	}

}
