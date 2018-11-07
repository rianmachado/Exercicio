package br.com.rian.sysmanager;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.rian.sysmanager.app.Applicacao1;
import br.com.rian.sysmanager.bo.TrainCompositionBO;
import br.com.rian.sysmanager.bo.TrainCompositionHelper;
import br.com.rian.sysmanager.entity.TrainComposition;
import br.com.rian.sysmanager.enums.CodigoErroSistema;
import br.com.rian.sysmanager.enums.Posicao;
import br.com.rian.sysmanager.exception.SistemaException;

@RunWith(MockitoJUnitRunner.class)
public class TrainCompositionTest {

	@InjectMocks
	private TrainCompositionBO trainCompositionBO;
	@InjectMocks
	private TrainCompositionHelper trainCompositionHelper;

	@Before
	public void prepareTest() {
		trainCompositionBO.setCompositionHelper(trainCompositionHelper);
	}

	@Test
	public void testOrdenarInteiros() {
		Integer[] ori = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		Integer[] numbers = { 10, 4, 2, 3, 5, 6, 8, 9, 1, 7 };

		trainCompositionHelper.sortAscending(numbers);

		assertTrue(Arrays.equals(ori, numbers));
	}

	@Test
	public void testCountNumbers() throws SistemaException {
		Integer[] numbers = { 10, 4, 2, 3, 5, 6, 8, 9, 1, 7 };

		Integer result;

		result = trainCompositionBO.countNumbers(numbers, 10);

		assertTrue(9 == result);

	}

	@Test
	public void testCountNumbersRetornoZero() throws SistemaException {
		Integer[] numbers = { 10, 4, 2, 3, 5, 6, 8, 9, 1, 7 };

		Integer result;

		result = trainCompositionBO.countNumbers(numbers, 1);

		assertTrue(0 == result);

	}

	@Test
	public void testCountNumbersVagoesInvalidos() {
		Integer[] numbers = null;
		try {

			trainCompositionBO.countNumbers(numbers, null);

		} catch (SistemaException e) {
			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_PARAMETRO_COMPOSICAO_VAGOES_INVALIDA.name().equals(codeErro));
		}
	}

	@Test
	public void testValidateTrainCompositionNomeVazio() {

		Integer[] vagoes = { 13, 7 };

		TrainComposition trainComposition = new TrainComposition(1L, "", vagoes);

		try {
			trainCompositionBO.validate(trainComposition);

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_PARAMETRO_TRAINCOMPOSITION_NOME_INVALIDO.name().equals(codeErro));
		}
	}

	@Test
	public void testValidateTrainCompositionNovoVagaoInvalido() {

		Integer[] vagoes = { 13, 7 };

		TrainComposition trainComposition = new TrainComposition(1L, "Trem das Onze", vagoes);

		trainComposition.setNovoVagao(0);
		try {

			trainCompositionBO.validate(trainComposition);

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_NOVO_VAGAO_INVALIDO.name().equals(codeErro));
		}
	}

	@Test
	public void testValidateTrainCompositionCodigoVazio() {

		Integer[] vagoes = { 13, 7 };

		TrainComposition trainComposition = new TrainComposition(0L, "Trem da tarde", vagoes);

		try {

			trainCompositionBO.validate(trainComposition);

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_PARAMETRO_TRAINCOMPOSITION_CODIGO_INVALIDO.name().equals(codeErro));
		}
	}

	@Test
	public void testValidateTrainCompositionNULL() {

		TrainComposition trainComposition = null;

		try {
			trainCompositionBO.validate(trainComposition);

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_INTERNO.name().equals(codeErro));
		}
	}

	@Test
	public void testAtracarVagaoComComposicaoInvalida() throws SistemaException {

		Integer[] vagoes = {};

		TrainComposition trainComposition = new TrainComposition(1L, "Trem da tarde", vagoes);

		try {

			trainCompositionBO.atracarVagao(trainComposition.getVagoes(), Posicao.ESQUERDA,	trainComposition.getNovoVagao());

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_PARAMETRO_COMPOSICAO_VAGOES_INVALIDA.name().equals(codeErro));
		}

	}
	
	@Test
	public void testAtracarVagaoComPosicaoInvalida() throws SistemaException {

		Integer[] vagoes = { 5, 6, 2, 3, 4, 8, 9, 10, 7, 1 };

		TrainComposition trainComposition = new TrainComposition(1L, "Trem da tarde", vagoes);

		try {

			trainCompositionBO.atracarVagao(trainComposition.getVagoes(), null,	trainComposition.getNovoVagao());

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_PARAMETRO_POSICAO_VAGAO_INVALIDO.name().equals(codeErro));
		}

	}
	

	@Test
	public void testAtracarVagaoEsquerda() throws SistemaException {

		Integer[] resultadoEsperado = { 13, 5, 6, 2, 3, 4, 8, 9, 10, 7, 1 };

		Integer[] vagoes = { 5, 6, 2, 3, 4, 8, 9, 10, 7, 1 };

		TrainComposition trainComposition = new TrainComposition(1L, "Trem da tarde", vagoes);

		trainComposition.setNovoVagao(13);

		vagoes = trainCompositionBO.atracarVagao(trainComposition.getVagoes(), Posicao.ESQUERDA,
				trainComposition.getNovoVagao());

		assertTrue(Arrays.equals(vagoes, resultadoEsperado));
	}

	@Test
	public void testAtracarVagaoDireita() throws SistemaException {

		Integer[] resultadoEsperado = { 5, 6, 2, 3, 4, 8, 9, 10, 7, 1, 21, 22, 23, 24, 25, 13 };

		Integer[] vagoes = { 5, 6, 2, 3, 4, 8, 9, 10, 7, 1, 21, 22, 23, 24, 25 };

		TrainComposition trainComposition = new TrainComposition(15L, "Trem da noite", vagoes);

		trainComposition.setNovoVagao(13);

		vagoes = trainCompositionBO.atracarVagao(trainComposition.getVagoes(), Posicao.DIREIRA,
				trainComposition.getNovoVagao());

		assertTrue(Arrays.equals(vagoes, resultadoEsperado));
	}

	@Test
	public void testCenarioPricipal() throws SistemaException {
		Integer[] resultadoEsperado = { 7, 13, 90 };

		Applicacao1 app = new Applicacao1();

		assertTrue(Arrays.equals(app.cenarioFuncional01(), resultadoEsperado));
	}

	@Test
	public void testIgualdadeTrainComposition() {

		TrainComposition trainComposition1 = new TrainComposition(1L, "Tem das Onze", new Integer[] { 13, 7 });

		trainComposition1.setNovoVagao(90);

		TrainComposition trainComposition2 = new TrainComposition(2L, "Tem das Onze", new Integer[] { 13, 7, 10 });

		trainComposition2.setNovoVagao(10);

		TrainComposition trainComposition3 = new TrainComposition();

		trainComposition3.setCodigo(2l);

		trainComposition3.setNome("Tem das nove");

		Set<TrainComposition> listaTrainComposition = new HashSet<TrainComposition>();

		listaTrainComposition.add(trainComposition1);

		listaTrainComposition.add(trainComposition2);

		listaTrainComposition.add(trainComposition3);

		assertTrue(listaTrainComposition.size() == 2);
	}

	@Test
	public void testCountNumbersComposisaoVagoesInvalida() {
		Integer[] numbers = {};

		try {

			trainCompositionBO.countNumbers(numbers, 10);

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_PARAMETRO_COMPOSICAO_VAGOES_INVALIDA.name().equals(codeErro));
		}
	}

	@Test
	public void testNovoVagaoComComposicaoInvalida() {

		try {

			trainCompositionHelper.validarNovoVagao(null, 20);

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_PARAMETRO_COMPOSICAO_VAGOES_INVALIDA.name().equals(codeErro));
		}
	}

	@Test
	public void testNovoVagaoComNumeroInvalido() {

		try {

			trainCompositionHelper.validarNovoVagao(new Integer[] { 23, 7 }, null);

		} catch (SistemaException e) {

			String codeErro = e.getCodigo().toString();

			assertTrue(CodigoErroSistema.ERRO_PARAMETRO_NUMERO_VAGAO_INVALIDO.name().equals(codeErro));
		}
	}

}
