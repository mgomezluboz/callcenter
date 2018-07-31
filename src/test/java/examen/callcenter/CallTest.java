package examen.callcenter;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.jupiter.api.Test;

public class CallTest {
	
	@After
	public void clearDispatch() {
		Dispatcher.getInstance().shutdown();
	}
	
	@Test
	public void llamadasMaximasConcurrentesPorThreadsIndependientes() throws InterruptedException, ExecutionException {
		List<TestRunnable> llamadas = new ArrayList<TestRunnable>();
		
		for(int i = 0; i < 10; i++) {
			TestRunnable testRunnable = new TestRunnable();
			llamadas.add(testRunnable);
			Thread t = new Thread(testRunnable);
			t.start();
			t.join();
		}
		
		for(TestRunnable call : llamadas) {
			Boolean b = false;
			if(null != call.callResult.get()) { b = call.callResult.get(); }
			assertTrue(b);
		}
	}

}
