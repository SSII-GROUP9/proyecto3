package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class metodos {
	
	@SuppressWarnings("resource")
	public static String compruebaSys() throws FileNotFoundException {
		@SuppressWarnings("unused")
		FileInputStream fis=null;
		try {
			fis = new FileInputStream("certificado\\");
			return "\\";
		}catch(Exception e){
			return "/";
		}
		
	}
	
	public static String generaHash(String m) throws NoSuchAlgorithmException {
		MessageDigest md= MessageDigest.getInstance("SHA-256");
        md.update(m.getBytes());
        byte[] mb = md.digest();
        return DatatypeConverter.printHexBinary(mb).toLowerCase();
	}
}
