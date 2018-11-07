package br.com.rian.sysmanager.app;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import br.com.rian.sysmanager.bo.TrainCompositionBO;
import br.com.rian.sysmanager.entity.TrainComposition;
import br.com.rian.sysmanager.enums.Posicao;
import br.com.rian.sysmanager.exception.SistemaException;
import br.com.rian.sysmanager.infra.SysManagerProperty;

public class Applicacao1 {

	private static final Logger LOG = Logger.getLogger(Applicacao1.class);

	TrainCompositionBO trainCompositionBo = new TrainCompositionBO();

	public static void main(String[] args) {
		try {
			Applicacao1 app = new Applicacao1();
			
			app.cenarioFuncional01();
			
		} catch (SistemaException e) {
			
			LOG.error(SysManagerProperty.bundle("system", new Locale("pt", "BR")).getString(e.getCodigo().getIdBundle()));
			
			LOG.error(e.getCause());
		}
	}

	public Integer[] cenarioFuncional01() throws SistemaException {

		TrainComposition trainComposition = new TrainComposition(1L, "Tem das Onze", new Integer[] { 13, 7 });
		
		trainComposition.setNovoVagao(90);
		
		trainCompositionBo.validate(trainComposition);

		Integer[] vagoesAtracados = trainCompositionBo.atracarVagao(trainComposition.getVagoes(), Posicao.ESQUERDA,	trainComposition.getNovoVagao());

		Stream<Integer> stream1 = Arrays.stream(vagoesAtracados);
		
		LOG.info("VAGOES ATRACADOS: ");
		
		stream1.forEach(v -> {
			
			LOG.info(v);
			
		});

		trainComposition.setVagoes(vagoesAtracados);
		
		trainCompositionBo.countNumbers(trainComposition.getVagoes(), 90);

		Stream<Integer> stream2 = Arrays.stream(trainComposition.getVagoes());
		
		LOG.info("VAGOES ATRACADOS ORDENADOS: ");
		
		stream2.forEach(v -> {
			
			LOG.info(v);
			
		});

		return trainComposition.getVagoes();
	}

}
