package examen.callcenter;

import java.util.concurrent.Future;

public class TestRunnable implements Runnable {
	
	private Dispatcher dispatcher;
	public boolean callResult;

	@Override
	public void run() {
		Future<Boolean> result = dispatcher.dispatchCall(new Llamada());
		try {
			callResult = result.get();
		} catch(Exception e) {
			//falla obtener el resultado del futuro
		}
	}
	
	public TestRunnable(Dispatcher d) {
		this.dispatcher = d;
	}

}
