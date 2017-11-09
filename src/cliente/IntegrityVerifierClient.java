package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;
import servidor.calculaMac;

public	class	IntegrityVerifierClient	{
		//Constructor que abre una conexión Socket para enviar mensaje/MAC al servidor
		public	IntegrityVerifierClient() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{					
			Boolean nuevoMensaje=true;
			try	{	
				while(nuevoMensaje) {
					System.setProperty("javax.net.ssl.keyStore", "c:\\SSLStore"); //puede que sea innecesario
					System.setProperty("javax.net.ssl.keyStorePassword", "123456"); //puede que sea innecesario
					
					SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();	//CAMBIAR
					SSLSocket socket = (SSLSocket) socketFactory.createSocket("localhost",7070);		//CAMBIAR
					socket.startHandshake();
					//Crea un PrintWriter para enviar mensaje/MAC al servidor
					PrintWriter	output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					//Crea un objeto BufferedReader	para leer la respuesta del servidor
					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					//antes de comenzar el envío del mensaje ...
					
					/*ahora tendriamos que pasar certificados.....
					Integer key=metodosAux.generaKey(Integer.parseInt(y), p, x2);
					*/
					
					//---------------------------------------------
					
					String userName = JOptionPane.showInputDialog(null,"Introduzca su mensaje que desea enviar al servidor: " );
					
					//Envío	del	mensaje	al servidor
					output.println(userName);
					output.flush();
					
					String alg = JOptionPane.showInputDialog("Algoritmo a usar (no poner nada implica HmacSHA256): ");
					if(alg.equals(""))
						alg="HmacSHA256";
					output.flush();
					
					//Habría que calcular el correspondiente MAC con la	clave compartida por servidor/cliente
//					String macdelMensaje = calculaMac.performMACTest(userName,alg,key);
//					output.println(macdelMensaje);
//					output.flush();
					
					//Habría que enviar el algoritmo de encriptacion que usamos
					output.println(alg);//HmacSHA256
					output.flush();
					
					//Importante para que el mensaje se	envíe
					output.flush();
					
					//Lee la respuesta del servidor
					String respuesta = input.readLine();
					//Muestra la respuesta al cliente
					JOptionPane.showMessageDialog(null,respuesta);
					
					String maux = JOptionPane.showInputDialog("¿Desea enviar otro mensaje? (y/n): ");
					
					if(maux.equals("n"))	//permite enviar más de un mensaje
						nuevoMensaje=false;
					
					//Se cierra	la conexion
					output.close();
					input.close();
					socket.close();
					
				}	//fin del while
				
			}catch(IOException ioException){	
				ioException.printStackTrace();	
			}	
		 //Salida de la	aplicacion	
			finally	{
				System.exit(0);
			}
		}
}
