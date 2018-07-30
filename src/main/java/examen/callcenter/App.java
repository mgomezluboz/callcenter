package examen.callcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 *  Main
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//un main para probar la ejecucion normal
    	System.out.println("Comienzo thread principal...");
    	
        Dispatcher dispatcher = new Dispatcher(6,3,1); //operarios,supervisores,directores
        List<Future<Boolean>> resultadosLlamadas = new ArrayList<Future<Boolean>>();
        
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada())); //1
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada()));
        resultadosLlamadas.add(dispatcher.dispatchCall(new Llamada())); //12

      //simulo una llamada entrando mas tarde
        try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			//si se interrumpiese, no es el caso
			e.printStackTrace();
		}
        
        dispatcher.dispatchCall(new Llamada());
        
        dispatcher.shutdown();
        
        try {
	        for(int i = 0; i < resultadosLlamadas.size(); i++) {
	        	System.out.println("Resultado llamada " + i + ": " + resultadosLlamadas.get(i).get().toString() + ".\n");
	        }
        } catch(Exception e) {
        	 System.out.println("Error leyendo valores futuros.");
        }
        
        System.out.println("Fin thread principal...");
    }
}
