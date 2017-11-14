package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import principal.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;


public class LoginServer {
	
		private	SSLServerSocket serverSocket;
		
		public LoginServer() throws	Exception {		
			System.setProperty("javax.net.ssl.keyStore", "certificado"+metodos.compruebaSys()+"SSLStore");
			System.setProperty("javax.net.ssl.keyStorePassword","123456");
			SSLServerSocketFactory socketFactory = (SSLServerSocketFactory)
					SSLServerSocketFactory.getDefault();
			serverSocket=(SSLServerSocket) socketFactory.createServerSocket(7070);
		}		
		
		public void runServer() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
			
			Map<String,String>usuarios=new HashMap<String,String>();	//vamos a simular que el servidor tiene usuarios y claves
			
			usuarios.put("antonio", "5ad384db70c4eb37359efbea24bfa35b45c142024d3e294e44120d0bbf99682f");	//clave - seguridad1
			usuarios.put("enrique", "79b31c54ca9df56867a827cabc0475e13b2dcfd4e0302d8ad16c3cfe76d2129d");	// - seguridad2
			usuarios.put("pite", "204ac9e190418e28cf21e55b5d9a6f4e96653b626c2b9dd5121d41fba71182d2");		// - seguridad3
			
			while(true) {
				try	{	
					Socket socket =	(Socket)serverSocket.accept();
					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter	output	= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					System.err.println("Esperando conexiones de clientes...");
					
					//	Se	lee	del	cliente	el user y passwd
					String user=input.readLine();
					String passwd=input.readLine();
					
					//ahora se debe comprobar que el user y passwd son correctos--- en caso de ser correcto enviamos OK
					
					try {	//el cliente necesita comprobar el tipo de respuesta para cortar o no la conexión.
						
						if(!usuarios.get(user).equals(passwd)) {
							System.err.println("La clave usada es incorrecta.\n");
							
							output.close();			
							input.close();			
							socket.close();
						}else {
							output.println("200");
							output.flush();
						}
						
					}catch(Exception e) {
						System.err.println("Usuario: "+user+ " no existe.\n");
						
						output.close();			
						input.close();			
						socket.close();
					}
					
					//si son correctos entonces ahora debemos recibir el mensaje
					String	mensaje	= input.readLine();	//mensaje que envia el cliente una vez dentro
					
					if(mensaje!=null)
						System.out.println("Mensaje recibido correctamente: "+mensaje+"\n");
					
					output.close();			
					input.close();			
					socket.close();	
					
			}catch (IOException	ioException){
				ioException.printStackTrace();
			}	
		}
	}
		
}
