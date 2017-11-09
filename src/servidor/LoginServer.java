package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class LoginServer {
	
		private	SSLServerSocket serverSocket;
		
		public LoginServer() throws	Exception {				
			SSLServerSocketFactory socketFactory = (SSLServerSocketFactory)
					SSLServerSocketFactory.getDefault();
			serverSocket=(SSLServerSocket) socketFactory.createServerSocket(7070);
		}		
		
		public void runServer() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
			
			List<Integer>contieneR=new ArrayList<Integer>();
			
			while(true) {
				try	{
					
					System.err.println("Esperando conexiones de clientes...");	
					Socket socket =	(Socket)serverSocket.accept();
					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter	output	= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					//comunicación segura token-
					
					/*	NECESARIO CAMBIAR MEDIDAS CON CERTIFICADOS
					
					List<Integer>valores=metodosAuxServer.generaValor(Integer.parseInt(p), Integer.parseInt(g));
					Integer y=valores.get(0);
					Integer y2=valores.get(1);
					
					output.println(y);
					output.flush();
					
					Integer key=metodosAuxServer.generaKey(y2, Integer.parseInt(p), Integer.parseInt(x));
					*/
					
					//--------------------------
					
				/*	if(contieneR.contains(key)) {
						System.err.println("Token usado - Posible ataque de replay - Tirando mensaje ..."
								+ "");
						output.close();			
						input.close();			
						socket.close();	
						break;
					}
					
					ES NECESARIO¿?
					
					contieneR.add(key);	*/
					//-------
					
					//	Se	lee	del	cliente	el user y passwd
					String user=input.readLine();
					String passwd=input.readLine();
					
					//ahora se debe comprobar que el user y passwd son correctos--- en caso de ser correcto enviamos OK
					
					
					//--------
					
					//si son correctos entonces ahora debemos recibir el mensaje
					String	mensaje	= input.readLine();	//mensaje que envia el cliente una vez dentro
				
					//System.out.println("Mensaje enviado por el cliente: "+mensaje);
					//	A	continuación	habría	que	calcular	el	mac	del	MensajeEnviado	que	podría	ser											
				
					String	macdelMensajeEnviado = input.readLine();	//QUIZAS AQUI DEBERIA SER EL PASSWD	
					//System.err.println("Mac del mensaje enviado: "+macdelMensajeEnviado+"\n");
				
					//especificacion del algoritmo mac- por defecto diremos macsha256
					
					/*String alg=input.readLine();
					System.out.println("Algoritmo Hmac utilizado: "+alg);
					//mac	del	MensajeCalculado -----
					
					//String macMensajeEnviado = null;
					String macdelMensajeCalculado = calculaMac.performMACTest(mensaje, alg,key);	*/
					
					//tratamiento de errores hmac----
			/*		if(macdelMensajeCalculado.equals("")) {
						System.err.println("Hmac No valido, estableciendo por defecto Hmac256");
					    macdelMensajeCalculado = calculaMac.performMACTest(mensaje, "HmacSHA256",key);
					}	*/
					// ------------------------------
					
				/*	if	(macdelMensajeEnviado.equals(macdelMensajeCalculado))	{		ahora habría que buscar otra compr	
							output.println("Mensaje enviado integro.");	
					}else{	
						output.println(	"Mensaje enviado no integro.");
					}		*/
					
					output.close();			
					input.close();			
					socket.close();	
					
			}catch (IOException	ioException){ioException.printStackTrace();}	
		}
	}
		
}
