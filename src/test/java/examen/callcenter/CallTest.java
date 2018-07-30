package examen.callcenter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CallTest {
	
	@Test
	public void llamadasMaximasConcurrentesPorThreadsIndependientes() {
		Dispatcher dispatcher = new Dispatcher(6,3,1);
		List<TestRunnable> llamadas = new ArrayList<TestRunnable>();
		
		for(int i = 0; i < 11; i++) {
			TestRunnable testRunnable = new TestRunnable(dispatcher);
			llamadas.add(testRunnable);
			new Thread(testRunnable).start();
		}
		
		for(TestRunnable call : llamadas) {
			assertTrue(call.callResult);
		}
	}
	
	@Test
	public void llamada11FallaPorFaltaDeDisponibilidad() {
		Dispatcher dispatcher = new Dispatcher(6,3,1);
		List<Llamada> llamadas = new ArrayList<Llamada>();
		
		for(int i = 0; i < 12; i++) {
			new Thread(new Runnable() {
				public void run() {
					Llamada testCall = new Llamada();
					llamadas.add(testCall);
					dispatcher.dispatchCall(testCall);
				}
			}).start();
		}
		
		assertEquals(5, llamadas.size());
		assertFalse(llamadas.get(5).finalizadaConExito);
	}

}
