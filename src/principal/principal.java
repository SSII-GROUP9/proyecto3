package principal;

import cliente.BYODClient;
import servidor.BYODServer;

public class principal {
	static public void main( String args[] ) {
		Thread hiloA = new Thread( (Runnable) new BYODServer(),"hiloA" );
        Thread hiloB = new Thread( (Runnable) new BYODClient(),"hiloB" );

        // Se arrancan los dos hilos, para que comiencen su ejecución
        hiloA.start();
        hiloB.start();
	}
}
