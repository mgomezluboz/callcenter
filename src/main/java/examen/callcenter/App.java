package examen.callcenter;

/**
 *  Main
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Comienzo thread principal...");
    	
        Dispatcher dispatcher = new Dispatcher();
        
        dispatcher.dispatchCall(new Llamada()); //1
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada());
        dispatcher.dispatchCall(new Llamada()); //12

      //simulo una llamada entrando mas tarde
        try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			//si se interrumpiese, no es el caso
			e.printStackTrace();
		}
        
        dispatcher.dispatchCall(new Llamada());
        
        dispatcher.shutdown();
        
        System.out.println("Fin thread principal...");
    }
}
