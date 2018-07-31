package examen.callcenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Empleado extends ThreadPoolExecutor {
	
	private String tipoEmpleado;

	public Empleado(int poolSize, long keepAliveTime, BlockingQueue<Runnable> workQueue, String tipo) {
		super(poolSize, poolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
		this.setRejectedExecutionHandler(handler);
		tipoEmpleado = tipo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Future<Boolean> submit(Callable llamada) {
		Future<Boolean> result = super.submit(llamada);
		System.out.println("Llamada " + ((Llamada)llamada).getId() + " atendida por " + tipoEmpleado + ".\n");
		return result;
	}

}
