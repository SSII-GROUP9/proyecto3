package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;

public	class	IntegrityVerifierClient	{
		//Constructor que abre una conexión Socket para enviar mensaje/MAC al servidor
		public	IntegrityVerifierClient() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{					
			Boolean nuevoMensaje=true;
			try	{	
				while(nuevoMensaje) {
					
					//Conexiones Socket
					SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
					SSLSocket socket = (SSLSocket) socketFactory.createSocket("localhost",7070);
					socket.startHandshake();
					
					//Buffer de lectura y escritura
					PrintWriter	output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					
					//Envio de credenciales
					String userName = JOptionPane.showInputDialog(null,"Introduzca su nombre de usuario: " );
					output.println(userName);
					output.flush();
					
					String password = JOptionPane.showInputDialog(null,"Introduzca su constraseña: " );
					output.println(password);
					output.flush();
					
					//Comprobacion de credenciales
					String status=input.readLine();
					if(status.equals("OK")){ //¿qué responde?¿200?¿OK?
						
						//envio del mensaje al servidor
						String message = JOptionPane.showInputDialog(null,"Introduzca el mensaje para el servidor: " );
						output.println(message);
						output.flush();
					}else{
						
						//Login incorrecto
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
						output.close();
						input.close();
						socket.close();
					}
					
					output.flush();
					
					//Lectura de la respuesta del servidor
					String respuesta = input.readLine();
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
