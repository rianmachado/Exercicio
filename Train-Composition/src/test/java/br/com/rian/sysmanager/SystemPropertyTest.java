package br.com.rian.sysmanager;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.rian.sysmanager.bo.TrainCompositionBO;
import br.com.rian.sysmanager.entity.TrainComposition;
import br.com.rian.sysmanager.exception.SistemaException;
import br.com.rian.sysmanager.infra.SysManagerProperty;

@RunWith(MockitoJUnitRunner.class)
public class SystemPropertyTest {
	
	
	@InjectMocks
	private TrainCompositionBO trainCompositionBO;

	@Test
	public void testBundleProperty() {

		Integer[] vagoes = { 5, 6, 2, 3, 4, 8, 9, 10, 7, 1 };

		TrainComposition trainComposition = new TrainComposition(1L, "Trem da tarde", vagoes);

		trainComposition.setNovoVagao(13);

		try {
			
			trainCompositionBO.atracarVagao(trainComposition.getVagoes(), null, trainComposition.getNovoVagao());
			
		} catch (SistemaException e) {
			
			String msgretornada = SysManagerProperty.bundle("system",new Locale("pt","BR")).getString(e.getCodigo().getIdBundle());
			
			assertNotNull(msgretornada);
			
		}

	}
}
