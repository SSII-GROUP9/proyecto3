package principal;

import servidor.*;

public class EjecutaServidor {
	static public void main( String args[] ) throws Exception {
		LoginServer	server	=	new	LoginServer();	
		server.runServer();	
		
	}
}
