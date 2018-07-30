package examen.callcenter;

import java.util.Random;
import java.util.concurrent.Callable;

public class Llamada implements Callable<Boolean> {
	
	private int duracion;
	private int id;
	private static int count = 0;
	public boolean finalizadaConExito = false;
	
	public Llamada() {
		this.id = ++count;
		this.duracion = new Random().ints(5000, 10000).iterator().nextInt();
	}

	@Override
	public Boolean call() {
		 System.out.println( "** Iniciando llamada " + this.id + ", duracion " + this.duracion + ". **\n" );
		 
		 try {
			Thread.sleep(duracion);
		} catch (InterruptedException e) {
			//Se interrumpe la llamada, no deberia ser relevante.
			e.printStackTrace();
		}
		 
		 System.out.println( "-- Fin llamada " + this.id + ". --\n" );
		 this.finalizadaConExito = true;
		 return this.finalizadaConExito;
	}
	
	public int getId() {
		return this.id;
	}

}
