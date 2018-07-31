package examen.callcenter;

import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;

public class Dispatcher {

	private Empleado operadores;
	private Empleado supervisores;
	private Empleado directores;
	private static Dispatcher instance;
	
	public static Dispatcher getInstance() {
		if (null == instance) {
			instance = new Dispatcher(6,3,1); //configuracion default
		}
		
		return instance;
	}
	
	public Future<Boolean> dispatchCall(Llamada call) {
		Future<Boolean> callResult = null;
		try {
			callResult = operadores.submit(call);
		} catch(RejectedExecutionException operadoresBusyException) {
			try {
				callResult = supervisores.submit(call);
			} catch(RejectedExecutionException supervisoresBusyException) {
				try {
					callResult = directores.submit(call);
				} catch(RejectedExecutionException directoresBusyException) {
					System.out.println( "------->  La llamada " + call.getId() + " no pudo ser atendida." );
					return callResult;
				}
			}
		}
		
		try {
			return callResult;
		} catch(Exception e) {
			System.out.println( "------->  Error atendiendo llamada " + call.getId() + " no pudo ser atendida." );
			return callResult;
		}
	}
	
	public Dispatcher(int cantidadOperadores, int cantidadSupervisores, int cantidadDirectores) {
		operadores = new Empleado(cantidadOperadores, 0, new SynchronousQueue<Runnable>(), "operador");
		supervisores = new Empleado(cantidadSupervisores, 0, new SynchronousQueue<Runnable>(), "supervisor");
		directores = new Empleado(cantidadDirectores, 0, new SynchronousQueue<Runnable>(), "director");
	}
	
	public void shutdown() {
		operadores.shutdown();
		supervisores.shutdown();
		directores.shutdown();
	}
	
}
