package examen.callcenter;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;

public class Dispatcher {

	private Empleado operadores = new Empleado(6, 0, new SynchronousQueue<Runnable>(), "operador");
	private Empleado supervisores = new Empleado(3, 0, new SynchronousQueue<Runnable>(), "supervisor");
	private Empleado directores = new Empleado(1, 0, new SynchronousQueue<Runnable>(), "director");
	
	public void dispatchCall(Llamada call) {
		try {
			operadores.execute(call);
		} catch(RejectedExecutionException operadoresBusyException) {
			try {
				supervisores.execute(call);
			} catch(RejectedExecutionException supervisoresBusyException) {
				try {
					directores.execute(call);
				} catch(RejectedExecutionException directoresBusyException) {
					System.out.println( "|------->  La llamada " + call.getId() + " no pudo ser atendida." );
				}
			}
		}
	}
	
	public Dispatcher() {
		
	}
	
	public void shutdown() {
		operadores.shutdown();
		supervisores.shutdown();
		directores.shutdown();
	}
	
}
