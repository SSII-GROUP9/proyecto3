package servidor;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class BYODServer implements Runnable{

	public static void main(String[] args) throws Exception {
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		LoginServer server = null;
		try {
			server = new	LoginServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			server.runServer();
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
