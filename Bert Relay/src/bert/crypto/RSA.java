package bert.crypto;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

import bert.rotmg.constants.NetworkConstants;

public class RSA {
	
	private static PublicKey KEY;

	static {
		try {
			X509EncodedKeySpec spec = new X509EncodedKeySpec(DatatypeConverter.parseBase64Binary(NetworkConstants.SERVER_PUBLIC_KEY));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			KEY = kf.generatePublic(spec);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static String encrypt(String string) {

		byte[] buf = string.getBytes(Charset.forName("UTF-8"));

		try {
			
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, KEY);

			buf = cipher.doFinal(buf);

			return DatatypeConverter.printBase64Binary(buf);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
