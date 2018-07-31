package examen.callcenter;

import java.util.concurrent.Future;

public class TestRunnable implements Runnable {
	
	public Future<Boolean> callResult;
	public Boolean result;

	@Override
	public void run() {
		Llamada call = new Llamada();
		callResult = Dispatcher.getInstance().dispatchCall(call);
	}
	
	public TestRunnable() {
	}

}
